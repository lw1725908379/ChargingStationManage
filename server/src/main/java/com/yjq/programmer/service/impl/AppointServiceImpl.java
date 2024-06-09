package com.yjq.programmer.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.AppointMapper;
import com.yjq.programmer.dao.ChargeMapper;
import com.yjq.programmer.dao.OrderMapper;
import com.yjq.programmer.dao.UserMapper;
import com.yjq.programmer.domain.*;
import com.yjq.programmer.dto.AppointDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.UserDTO;
import com.yjq.programmer.enums.AppointStateEnum;
import com.yjq.programmer.enums.ChargeStateEnum;
import com.yjq.programmer.service.IAppointService;
import com.yjq.programmer.utils.CommonUtil;
import com.yjq.programmer.utils.CopyUtil;
import com.yjq.programmer.utils.UuidUtil;
import com.yjq.programmer.utils.ValidateEntityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wenLiu
 * @create 2023-12-30 17:06
 */
@Service
@Transactional
public class AppointServiceImpl implements IAppointService {

    @Resource
    private AppointMapper appointMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ChargeMapper chargeMapper;

    @Resource
    private OrderMapper orderMapper;

    /**
     * 保存预约信息
     * @param appointDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> saveAppoint(AppointDTO appointDTO) {
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(appointDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseDTO.errorByMsg(validate);
        }
        Appoint appoint = CopyUtil.copy(appointDTO, Appoint.class);
        Charge charge = chargeMapper.selectByPrimaryKey(appoint.getChargeId());
        if(ChargeStateEnum.REPAIR.getCode().equals(charge.getState())) {
            return ResponseDTO.errorByMsg(CodeMsg.CHARGE_REPAIR_ERROR);
        }
        User user = userMapper.selectByPrimaryKey(appoint.getUserId());
        if(user == null) {
            return ResponseDTO.errorByMsg(CodeMsg.USER_NOT_EXIST);
        }
        if(CommonUtil.isEmpty(appoint.getId())) {
            // 添加操作
            if(user.getRate() < 80) {
                return ResponseDTO.errorByMsg(CodeMsg.RATE_NOT_ENOUGH);
            }
            appoint.setId(UuidUtil.getShortUuid());
            appoint.setCreateTime(new Date());
            // 判断预约日期时间段是否重复
            if(isDayExist(appoint, "")){
                return ResponseDTO.errorByMsg(CodeMsg.APPOINT_DAY_EXIST);
            }
            if(appointMapper.insertSelective(appoint) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.APPOINT_ADD_ERROR);
            }
        } else {
            // 修改操作
            // 判断预约日期时间段是否重复
            if(isDayExist(appoint, appoint.getId())){
                return ResponseDTO.errorByMsg(CodeMsg.APPOINT_DAY_EXIST);
            }
            if(appointMapper.updateByPrimaryKeySelective(appoint) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.APPOINT_EDIT_ERROR);
            }
        }
        return ResponseDTO.successByMsg(true, "保存成功！");
    }

    /**
     * 获取已预约的日期
     * @return
     */
    @Override
    public ResponseDTO<List<AppointDTO>> getAppointDate(AppointDTO appointDTO) {
        AppointExample appointExample = new AppointExample();
        AppointExample.Criteria criteria = appointExample.createCriteria();
        criteria.andStateNotEqualTo(AppointStateEnum.CANCEL.getCode());
        if(!CommonUtil.isEmpty(appointDTO.getChargeId())) {
            criteria.andChargeIdEqualTo(appointDTO.getChargeId());
        }
        if(!CommonUtil.isEmpty(appointDTO.getUserId())) {
            criteria.andUserIdEqualTo(appointDTO.getUserId());
        }
        List<Appoint> appointList = appointMapper.selectByExample(appointExample);
        /*appointList 中筛选出当前时间之前尚未结束的预约，
        并将这些预约重新收集成一个列表。
        具体来说，它检查每个预约的结束时间，并过滤掉那些已经过期的预约。*/
        appointList = appointList.stream().filter(item -> {
            String[] splitTime = item.getTime().split("-");
            // splitTime[0] :6:00  end : splitTime[1] : 8:00
            Date endDate = CommonUtil.getFormatterDate(CommonUtil.getFormatterDate(item.getDay(),
                    "yyyy-MM-dd") + " " + splitTime[1], "yyyy-MM-dd HH:mm");
            return new Date().getTime() <= endDate.getTime();//返回当前时间往后的预约数据。当前时间之前的数据前端处理（不可约）
        }).collect(Collectors.toList());
        return ResponseDTO.success(CopyUtil.copyList(appointList, AppointDTO.class));
    }

    /**
     * 分页获取预约数据
     * @param pageDTO
     * @return
     */
    @Override
    public ResponseDTO<PageDTO<AppointDTO>> getAppointList(PageDTO<AppointDTO> pageDTO) {
        AppointExample appointExample = new AppointExample();
        // 不知道当前页多少，默认为第一页
        if(pageDTO.getPage() == null){
            pageDTO.setPage(1);
        }
        // 不知道每页多少条记录，默认为每页5条记录
        if(pageDTO.getSize() == null){
            pageDTO.setSize(5);
        }
        AppointExample.Criteria c1 = appointExample.createCriteria();
        if(pageDTO.getParam() != null) {
            AppointDTO appointDTO = pageDTO.getParam();
            if(!CommonUtil.isEmpty(appointDTO.getUserId())) {
                c1.andUserIdEqualTo(appointDTO.getUserId());
            } else {
                UserExample userExample = new UserExample();
                userExample.createCriteria().andUsernameLike("%" + appointDTO.getUsername() + "%");
                List<String> userIdList = userMapper.selectByExample(userExample).stream().map(User::getId).collect(Collectors.toList());
                if(userIdList.size() == 0) {
                    pageDTO.setTotal(0L);
                    pageDTO.setList(new ArrayList<>());
                    return ResponseDTO.success(pageDTO);
                }
                c1.andUserIdIn(userIdList);
            }
            if(!(CommonUtil.isEmpty(appointDTO.getChargeId())) && !"0".equals(appointDTO.getChargeId())) {
                c1.andChargeIdEqualTo(appointDTO.getChargeId());
            }
        }
        appointExample.setOrderByClause("create_time desc");
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getSize());
        // 分页查出预约数据
        List<Appoint> appointList = appointMapper.selectByExample(appointExample);
        PageInfo<Appoint> pageInfo = new PageInfo<>(appointList);
        // 获取数据的总数
        pageDTO.setTotal(pageInfo.getTotal());
        // 将domain类型数据  转成 DTO类型数据
        List<AppointDTO> appointDTOList = CopyUtil.copyList(appointList, AppointDTO.class);
        for(AppointDTO appointDTO : appointDTOList) {
            User user = userMapper.selectByPrimaryKey(appointDTO.getUserId());
            appointDTO.setUserDTO(CopyUtil.copy(user, UserDTO.class));
            // 当前时间超过结束时间，且状态为待使用的预约，状态自动改为已取消
            // 当前时间超过结束时间，且状态为充电中的预约，状态自动改为已完成
            String[] splitTime = appointDTO.getTime().split("-");
            Date endDate = CommonUtil.getFormatterDate(CommonUtil.getFormatterDate(appointDTO.getDay(), "yyyy-MM-dd") + " " + splitTime[1], "yyyy-MM-dd HH:mm");
            Appoint appoint = CopyUtil.copy(appointDTO, Appoint.class);
            if(new Date().getTime() > endDate.getTime()) {
                if(AppointStateEnum.WAIT.getCode().equals(appointDTO.getState())) {
                    appoint.setState(AppointStateEnum.CANCEL.getCode());
                    appointDTO.setState(AppointStateEnum.CANCEL.getCode());
                } else if(AppointStateEnum.USE.getCode().equals(appointDTO.getState())) {
                    appoint.setState(AppointStateEnum.OVER.getCode());
                    appoint.setEndTime(endDate);
                    appointDTO.setEndTime(endDate);
                    appointDTO.setState(AppointStateEnum.OVER.getCode());
                    Charge charge = chargeMapper.selectByPrimaryKey(appointDTO.getChargeId());
                    BigDecimal totalPrice = charge.getPrice().multiply(new BigDecimal(DateUtil.between(appointDTO.getStartTime(), endDate, DateUnit.MINUTE)));
                    Order order = new Order();
                    order.setId(UuidUtil.getShortUuid());
                    order.setCreateTime(new Date());
                    order.setUserId(appointDTO.getUserId());
                    order.setChargeId(appointDTO.getChargeId());
                    order.setChargeName(appointDTO.getChargeName());
                    order.setTotalPrice(totalPrice);
                    if(orderMapper.insertSelective(order) == 0) {
                        return ResponseDTO.errorByMsg(CodeMsg.ORDER_ADD_ERROR);
                    }
                }
                appointMapper.updateByPrimaryKeySelective(appoint);
            }
        }
        pageDTO.setList(appointDTOList);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * 更改预约状态信息
     * @param appointDTO
     * @return
     */
    @Override
    public ResponseDTO<BigDecimal> updateAppointState(AppointDTO appointDTO) {
        if(CommonUtil.isEmpty(appointDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        Appoint appoint = CopyUtil.copy(appointDTO, Appoint.class);
        Appoint appointDB = appointMapper.selectByPrimaryKey(appointDTO.getId());
        if(AppointStateEnum.CANCEL.getCode().equals(appointDTO.getState())) {
            // 取消预约
            if(appointMapper.updateByPrimaryKeySelective(appoint) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.APPOINT_EDIT_ERROR);
            }
        } else if (AppointStateEnum.USE.getCode().equals(appointDTO.getState())) {
            // 开始充电
            appoint.setStartTime(new Date());
            if(appointMapper.updateByPrimaryKeySelective(appoint) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.APPOINT_EDIT_ERROR);
            }
        } else if (AppointStateEnum.OVER.getCode().equals(appointDTO.getState())) {
            // 结束充电
            Charge charge = chargeMapper.selectByPrimaryKey(appointDTO.getChargeId());
            BigDecimal totalPrice = charge.getPrice().multiply(new BigDecimal(appoint.getTime()));
            Order order = new Order();
            order.setId(UuidUtil.getShortUuid());
            order.setCreateTime(new Date());
            order.setUserId(appointDB.getUserId());
            order.setChargeId(appoint.getChargeId());
            order.setChargeName(appoint.getChargeName());
            order.setTotalPrice(totalPrice);
            if(orderMapper.insertSelective(order) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.ORDER_ADD_ERROR);
            }
            appoint.setTime(null);
            if(appointMapper.updateByPrimaryKeySelective(appoint) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.APPOINT_EDIT_ERROR);
            }
            return ResponseDTO.success(totalPrice);
        }
        return ResponseDTO.success(new BigDecimal(0.00));
    }

    /**
     * 删除预约信息
     * @param appointDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> deleteAppoint(AppointDTO appointDTO) {
        if(CommonUtil.isEmpty(appointDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        if(appointMapper.deleteByPrimaryKey(appointDTO.getId()) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.APPOINT_DELETE_ERROR);
        }
        return ResponseDTO.successByMsg(true, "删除成功！");
    }

    /**
     * 获取全部预约数据
     * @param appointDTO
     * @return
     */
    @Override
    public ResponseDTO<List<AppointDTO>> getAllAppoint(AppointDTO appointDTO) {
        AppointExample appointExample = new AppointExample();
        AppointExample.Criteria criteria = appointExample.createCriteria();
        if(!CommonUtil.isEmpty(appointDTO.getUserId())) {
            criteria.andUserIdEqualTo(appointDTO.getUserId());
        }
        List<Appoint> appointList = appointMapper.selectByExample(appointExample);
        return ResponseDTO.success(CopyUtil.copyList(appointList, AppointDTO.class));
    }


    /**
     * 判断预约日期时间段是否重复
     * @param appoint
     * @param id
     * @return
     */
    public Boolean isDayExist(Appoint appoint, String id) {
        AppointExample appointExample = new AppointExample();
        appointExample.createCriteria().andDayEqualTo(appoint.getDay()).andChargeIdEqualTo(appoint.getChargeId()).andTimeEqualTo(appoint.getTime());
        List<Appoint> selectedAppointList = appointMapper.selectByExample(appointExample);
        if (selectedAppointList != null && selectedAppointList.size() > 0) {
            if (selectedAppointList.size() > 1) {
                return true; //出现重复
            }
            if (!selectedAppointList.get(0).getId().equals(id)) {
                return true; //出现重复
            }
        }
        return false;//没有重复
    }
}
