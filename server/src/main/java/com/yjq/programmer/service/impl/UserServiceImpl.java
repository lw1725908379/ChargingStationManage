package com.yjq.programmer.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.*;
import com.yjq.programmer.domain.*;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.RateDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.UserDTO;
import com.yjq.programmer.enums.RateTypeEnum;
import com.yjq.programmer.enums.RoleEnum;
import com.yjq.programmer.service.IUserService;
import com.yjq.programmer.utils.CommonUtil;
import com.yjq.programmer.utils.CopyUtil;
import com.yjq.programmer.utils.UuidUtil;
import com.yjq.programmer.utils.ValidateEntityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wenLiu
 * @create 2023-12-29 13:41
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RateMapper rateMapper;

    //使用Spring Frame 默认配置类
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AppointMapper appointMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private WarnMapper warnMapper;

    @Resource
    private CommentMapper commentMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 注册用户信息
     * @param userDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> registerUser(UserDTO userDTO) {
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(userDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseDTO.errorByMsg(validate);
        }
        User user = CopyUtil.copy(userDTO, User.class);
        // 判断手机号码是否存在
        if (isPhoneExist(user, "")) {
            return ResponseDTO.errorByMsg(CodeMsg.USER_PHONE_EXIST);
        }
        // 判断用户昵称是否重复
        if (isUsernameExist(user, "")) {
            return ResponseDTO.errorByMsg(CodeMsg.USER_NAME_EXIST);
        }
        user.setId(UuidUtil.getShortUuid());
        user.setRoleId(RoleEnum.USER.getCode());
        if (userMapper.insertSelective(user) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.USER_REGISTER_ERROR);
        }
        return ResponseDTO.successByMsg(true, "注册成功！");
    }

    /**
     * 分页获取用户数据
     * @param pageDTO
     * @return
     */
    @Override
    public ResponseDTO<PageDTO<UserDTO>> getUserList(PageDTO<UserDTO> pageDTO) {
        UserExample userExample = new UserExample();
        // 不知道当前页多少，默认为第一页
        if(pageDTO.getPage() == null){
            pageDTO.setPage(1);
        }
        // 不知道每页多少条记录，默认为每页5条记录
        if(pageDTO.getSize() == null){
            pageDTO.setSize(5);
        }
        UserExample.Criteria c1 = userExample.createCriteria();
        if(pageDTO.getParam() != null) {
            UserDTO userDTO = pageDTO.getParam();
            if(userDTO.getRoleId() != null && userDTO.getRoleId() != 0) {
                c1.andRoleIdEqualTo(userDTO.getRoleId());
            }
            if(!CommonUtil.isEmpty(userDTO.getUsername())) {
                c1.andUsernameLike("%" + userDTO.getUsername() + "%");
            }
            if(!CommonUtil.isEmpty(userDTO.getId())) {
                c1.andIdEqualTo(userDTO.getId());
            }
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getSize());
        // 分页查出用户数据
        // select * from user where username like "%张%" limit 0,(page-1)*size;
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        // 获取数据的总数
        pageDTO.setTotal(pageInfo.getTotal());
        // 将domain类型数据  转成 DTO类型数据
        List<UserDTO> userDTOList = CopyUtil.copyList(userList, UserDTO.class);
        pageDTO.setList(userDTOList);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * 保存用户信息
     * @param userDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> saveUser(UserDTO userDTO) {
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(userDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseDTO.errorByMsg(validate);
        }
        User user = CopyUtil.copy(userDTO, User.class);
        if(CommonUtil.isEmpty(user.getId())) {
            // 添加操作
            // 判断手机号码是否存在
            if(isPhoneExist(user, "")){
                return ResponseDTO.errorByMsg(CodeMsg.USER_PHONE_EXIST);
            }
            // 判断用户昵称是否重复
            if (isUsernameExist(user, "")) {
                return ResponseDTO.errorByMsg(CodeMsg.USER_NAME_EXIST);
            }
            user.setId(UuidUtil.getShortUuid());
            if(userMapper.insertSelective(user) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.USER_ADD_ERROR);
            }
        } else {
            // 修改操作
            // 判断手机号码是否存在
            if(isPhoneExist(user, user.getId())){
                return ResponseDTO.errorByMsg(CodeMsg.USER_PHONE_EXIST);
            }
            // 判断用户昵称是否重复
            if (isUsernameExist(user, user.getId())) {
                return ResponseDTO.errorByMsg(CodeMsg.USER_NAME_EXIST);
            }
            ResponseDTO<UserDTO> loginUser = getLoginUser(userDTO.getToken());
            if(loginUser.getCode() != 0) {
                return ResponseDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
            }
            User userDB = userMapper.selectByPrimaryKey(user.getId());
            Rate rate = new Rate();
            rate.setId(UuidUtil.getShortUuid());
            rate.setNowScore(user.getRate());
            rate.setCreateTime(new Date());
            rate.setUserId(user.getId());
            rate.setContent("管理员设置信誉积分！");
            if(userDB.getRate() < user.getRate()) {
                rate.setType(RateTypeEnum.ADD.getCode());
                rate.setScore(user.getRate() - userDB.getRate());
                rateMapper.insertSelective(rate);
            }
            if(userDB.getRate() > user.getRate()) {
                rate.setType(RateTypeEnum.PLUS.getCode());
                rate.setScore(userDB.getRate() - user.getRate());
                rateMapper.insertSelective(rate);
            }

            if(userMapper.updateByPrimaryKeySelective(user) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.USER_EDIT_ERROR);
            }
            UserDTO loginUserDTO = loginUser.getData();
            if(user.getId().equals(loginUserDTO.getId())) {
                // 如果是修改个人信息  则更新redis中数据
                loginUserDTO = CopyUtil.copy(userMapper.selectByPrimaryKey(user.getId()), UserDTO.class);
                loginUserDTO.setToken(userDTO.getToken());
                stringRedisTemplate.opsForValue().set("USER_" + userDTO.getToken(), JSON.toJSONString(loginUserDTO), 3600, TimeUnit.SECONDS);
            }
        }
        return ResponseDTO.successByMsg(true, "保存成功！");
    }

    /**
     * 删除用户信息
     * @param userDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> deleteUser(UserDTO userDTO) {
        if(CommonUtil.isEmpty(userDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 删除用户信息
        if(userMapper.deleteByPrimaryKey(userDTO.getId()) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.USER_DELETE_ERROR);
        }
        // 删除用户有关的订单信息
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andUserIdEqualTo(userDTO.getId());
        orderMapper.deleteByExample(orderExample);
        // 删除用户有关的预约信息
        AppointExample appointExample = new AppointExample();
        appointExample.createCriteria().andUserIdEqualTo(userDTO.getId());
        appointMapper.deleteByExample(appointExample);
        // 删除用户有关的信誉积分信息
        RateExample rateExample = new RateExample();
        rateExample.createCriteria().andUserIdEqualTo(userDTO.getId());
        rateMapper.deleteByExample(rateExample);
        // 删除用户有关的告警信息
        WarnExample warnExample = new WarnExample();
        warnExample.createCriteria().andUserIdEqualTo(userDTO.getId());
        warnMapper.deleteByExample(warnExample);
        // 删除用户有关的留言信息
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andUserIdEqualTo(userDTO.getId());
        commentMapper.deleteByExample(commentExample);


        return ResponseDTO.successByMsg(true, "删除成功！");
    }

    /**
     * 用户登录操作
     * @param userDTO
     * @return
     */
    @Override
    public ResponseDTO<UserDTO> login(UserDTO userDTO) {
        // 进行是否为空判断
        if(CommonUtil.isEmpty(userDTO.getUsername())){
            return ResponseDTO.errorByMsg(CodeMsg.USERNAME_EMPTY);
        }
        if(CommonUtil.isEmpty(userDTO.getPassword())){
            return ResponseDTO.errorByMsg(CodeMsg.PASSWORD_EMPTY);
        }
        // 对比昵称和密码是否正确
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(userDTO.getUsername()).andPasswordEqualTo(userDTO.getPassword());
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList == null || userList.size() != 1){
            return ResponseDTO.errorByMsg(CodeMsg.USERNAME_PASSWORD_ERROR);
        }
        //密码置空
        User user = userList.get(0);
        user.setPassword("");
        //TODO: 用jwt util 颁发令牌
        // 生成登录token并存入Redis中
        UserDTO selectedUserDto = CopyUtil.copy(user, UserDTO.class);
        String token = UuidUtil.getShortUuid();
        selectedUserDto.setToken(token);
        //把token存入redis中 有效期1小时
        stringRedisTemplate.opsForValue().set("USER_" + token, JSON.toJSONString(selectedUserDto), 3600, TimeUnit.SECONDS);
        return ResponseDTO.successByMsg(selectedUserDto, "登录成功！");
    }

    /**
     * 检查用户是否登录
     * @param userDTO
     * @return
     */
    @Override
    public ResponseDTO<UserDTO> checkLogin(UserDTO userDTO) {
        System.out.println(userDTO);
        if(userDTO == null || CommonUtil.isEmpty(userDTO.getToken())){
            return ResponseDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }

        ResponseDTO<UserDTO> responseDTO = getLoginUser(userDTO.getToken());
        if(responseDTO.getCode() != 0){
            return responseDTO;
        }
        logger.info("获取到的登录信息={}", responseDTO.getData());
        return ResponseDTO.success(responseDTO.getData());
    }

    /**
     * 获取某用户的积分明细
     * @param userDTO
     * @return
     */
    @Override
    public ResponseDTO<List<RateDTO>> getRateList(UserDTO userDTO) {
        if(CommonUtil.isEmpty(userDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        RateExample rateExample = new RateExample();
        RateExample.Criteria criteria = rateExample.createCriteria();
        criteria.andUserIdEqualTo(userDTO.getId());
        rateExample.setOrderByClause("create_time desc");
        List<Rate> rateList = rateMapper.selectByExample(rateExample);
        return ResponseDTO.success(CopyUtil.copyList(rateList, RateDTO.class));
    }

    /**
     * 用户退出登录
     * @param userDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> logout(UserDTO userDTO) {
        if(!CommonUtil.isEmpty(userDTO.getToken())){
            // token不为空  清除redis中数据
            stringRedisTemplate.delete("USER_" + userDTO.getToken());
        }
        return ResponseDTO.successByMsg(true, "退出登录成功！");
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public ResponseDTO<UserDTO> getLoginUser(String token){
        logger.info("从redis获取键为={}的token" , "USER_" + token);
        String value = stringRedisTemplate.opsForValue().get("USER_" + token);
        logger.info("从redis中获取到的登录信息={}", value=="" ? "null" : value);
        if(CommonUtil.isEmpty(value)){
            return ResponseDTO.errorByMsg(CodeMsg.USER_SESSION_EXPIRED);
        }
        UserDTO selectedUserDTO = JSON.parseObject(value, UserDTO.class);
        return ResponseDTO.success(selectedUserDTO);
    }


    /**
     * 判断用户手机号是否重复
     * @param user
     * @param id
     * @return
     */
    public Boolean isPhoneExist(User user, String id) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneEqualTo(user.getPhone());
        List<User> selectedUserList = userMapper.selectByExample(userExample);
        if (selectedUserList != null && selectedUserList.size() > 0) {
            if (selectedUserList.size() > 1) {
                return true; //出现重复
            }
            if (!selectedUserList.get(0).getId().equals(id)) {
                return true; //出现重复
            }
        }
        return false;//没有重复
    }

    /**
     * 判断用户昵称是否重复
     * @param user
     * @param id
     * @return
     */
    public Boolean isUsernameExist(User user, String id) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> selectedUserList = userMapper.selectByExample(userExample);
        if (selectedUserList != null && selectedUserList.size() > 0) {
            if (selectedUserList.size() > 1) {
                return true; //出现重复
            }
            if (!selectedUserList.get(0).getId().equals(id)) {
                return true; //出现重复
            }
        }
        return false;//没有重复
    }
}
