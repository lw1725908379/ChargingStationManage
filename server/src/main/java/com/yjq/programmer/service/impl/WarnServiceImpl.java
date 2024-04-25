package com.yjq.programmer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.RateMapper;
import com.yjq.programmer.dao.UserMapper;
import com.yjq.programmer.dao.WarnMapper;
import com.yjq.programmer.domain.*;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.UserDTO;
import com.yjq.programmer.dto.WarnDTO;
import com.yjq.programmer.enums.RateTypeEnum;
import com.yjq.programmer.service.IWarnService;
import com.yjq.programmer.utils.CommonUtil;
import com.yjq.programmer.utils.CopyUtil;
import com.yjq.programmer.utils.UuidUtil;
import com.yjq.programmer.utils.ValidateEntityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2023-12-31 12:14
 */
@Service
@Transactional
public class WarnServiceImpl implements IWarnService {

    @Resource
    private WarnMapper warnMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RateMapper rateMapper;

    /**
     * 分页获取告警数据
     * @param pageDTO
     * @return
     */
    @Override
    public ResponseDTO<PageDTO<WarnDTO>> getWarnList(PageDTO<WarnDTO> pageDTO) {
        WarnExample warnExample = new WarnExample();
        // 不知道当前页多少，默认为第一页
        if(pageDTO.getPage() == null){
            pageDTO.setPage(1);
        }
        // 不知道每页多少条记录，默认为每页5条记录
        if(pageDTO.getSize() == null){
            pageDTO.setSize(5);
        }
        WarnExample.Criteria c1 = warnExample.createCriteria();
        if(pageDTO.getParam() != null) {
            WarnDTO warnDTO = pageDTO.getParam();
            if(!CommonUtil.isEmpty(warnDTO.getUserId())) {
                c1.andUserIdEqualTo(warnDTO.getUserId());
            } else {
                UserExample userExample = new UserExample();
                userExample.createCriteria().andUsernameLike("%" + warnDTO.getUsername() + "%");
                List<String> userIdList = userMapper.selectByExample(userExample).stream().map(User::getId).collect(Collectors.toList());
                if(userIdList.size() == 0) {
                    pageDTO.setTotal(0L);
                    pageDTO.setList(new ArrayList<>());
                    return ResponseDTO.success(pageDTO);
                }
                c1.andUserIdIn(userIdList);
            }
        }
        warnExample.setOrderByClause("create_time desc");
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getSize());
        // 分页查出告警数据
        List<Warn> warnList = warnMapper.selectByExample(warnExample);
        PageInfo<Warn> pageInfo = new PageInfo<>(warnList);
        // 获取数据的总数
        pageDTO.setTotal(pageInfo.getTotal());
        // 将domain类型数据  转成 DTO类型数据
        List<WarnDTO> warnDTOList = CopyUtil.copyList(warnList, WarnDTO.class);
        for(WarnDTO warnDTO : warnDTOList) {
            User user = userMapper.selectByPrimaryKey(warnDTO.getUserId());
            warnDTO.setUserDTO(CopyUtil.copy(user, UserDTO.class));
            RateExample rateExample = new RateExample();
            rateExample.createCriteria().andWarnIdEqualTo(warnDTO.getId());
            List<Rate> rateList = rateMapper.selectByExample(rateExample);
            if(rateList.size() > 0) {
                warnDTO.setRate(rateList.get(0).getScore());
            } else {
                warnDTO.setRate(0);
            }
        }
        pageDTO.setList(warnDTOList);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * 保存告警信息
     * @param warnDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> saveWarn(WarnDTO warnDTO) {
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(warnDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseDTO.errorByMsg(validate);
        }
        Warn warn = CopyUtil.copy(warnDTO, Warn.class);
        User user = userMapper.selectByPrimaryKey(warnDTO.getUserId());
        if(user == null) {
            return ResponseDTO.errorByMsg(CodeMsg.USER_NOT_EXIST);
        }
        if(CommonUtil.isEmpty(warn.getId())) {
            // 添加操作
            warn.setId(UuidUtil.getShortUuid());
            warn.setCreateTime(new Date());
            Rate rate = new Rate();
            rate.setId(UuidUtil.getShortUuid());
            rate.setCreateTime(new Date());
            rate.setType(RateTypeEnum.PLUS.getCode());
            rate.setScore(warnDTO.getRate());
            rate.setUserId(warn.getUserId());
            rate.setWarnId(warn.getId());
            rate.setContent(warn.getContent());
            user.setRate(user.getRate() - rate.getScore());
            if (user.getRate() < 0) {
                user.setRate(0);
            }
            rate.setNowScore(user.getRate());
            if(rate.getScore() != 0 && rateMapper.insertSelective(rate) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.RATE_ADD_ERROR);
            }
            if(userMapper.updateByPrimaryKeySelective(user) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.USER_EDIT_ERROR);
            }
            if(warnMapper.insertSelective(warn) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.WARN_ADD_ERROR);
            }
        } else {
            // 修改操作
            RateExample rateExample = new RateExample();
            rateExample.createCriteria().andWarnIdEqualTo(warn.getId());
            List<Rate> rateList = rateMapper.selectByExample(rateExample);
            if(rateList.size() == 0) {
                Rate rate = new Rate();
                rate.setId(UuidUtil.getShortUuid());
                rate.setContent(warn.getContent());
                rate.setCreateTime(new Date());
                rate.setType(RateTypeEnum.PLUS.getCode());
                rate.setScore(warnDTO.getRate());
                rate.setUserId(warn.getUserId());
                rate.setWarnId(warn.getId());
                user.setRate(user.getRate() - rate.getScore());
                if (user.getRate() < 0) {
                    user.setRate(0);
                }
                rate.setNowScore(user.getRate());
                if(rate.getScore() != 0 && rateMapper.insertSelective(rate) == 0) {
                    return ResponseDTO.errorByMsg(CodeMsg.RATE_ADD_ERROR);
                }
                if(userMapper.updateByPrimaryKeySelective(user) == 0) {
                    return ResponseDTO.errorByMsg(CodeMsg.USER_EDIT_ERROR);
                }
            } else {
                Rate rate = rateList.get(0);
                user.setRate(user.getRate() + rate.getScore() - warnDTO.getRate());
                rate.setScore(warnDTO.getRate());
                if (user.getRate() < 0) {
                    user.setRate(0);
                }
                rate.setNowScore(user.getRate());
                if(rate.getScore() == 0) {
                    if(rateMapper.deleteByPrimaryKey(rate.getId()) == 0) {
                        return ResponseDTO.errorByMsg(CodeMsg.RATE_DELETE_ERROR);
                    }
                } else {
                    if(rateMapper.updateByPrimaryKeySelective(rate) == 0) {
                        return ResponseDTO.errorByMsg(CodeMsg.RATE_EDIT_ERROR);
                    }
                }
                if(userMapper.updateByPrimaryKeySelective(user) == 0) {
                    return ResponseDTO.errorByMsg(CodeMsg.USER_EDIT_ERROR);
                }
            }
            if(warnMapper.updateByPrimaryKeySelective(warn) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.WARN_EDIT_ERROR);
            }
        }
        return ResponseDTO.successByMsg(true, "保存成功！");
    }

    /**
     * 删除告警信息
     * @param warnDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> deleteWarn(WarnDTO warnDTO) {
        if(CommonUtil.isEmpty(warnDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 删除告警信息
        if(warnMapper.deleteByPrimaryKey(warnDTO.getId()) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.WARN_DELETE_ERROR);
        }
        RateExample rateExample = new RateExample();
        rateExample.createCriteria().andWarnIdEqualTo(warnDTO.getId());
        List<Rate> rateList = rateMapper.selectByExample(rateExample);
        if(rateList.size() > 0) {
            Rate rate = rateList.get(0);
            User user = userMapper.selectByPrimaryKey(rate.getUserId());
            rate.setId(UuidUtil.getShortUuid());
            rate.setType(RateTypeEnum.ADD.getCode());
            rate.setCreateTime(new Date());
            rate.setContent("告警删除撤销，恢复信誉积分！");
            if(user.getRate() + rate.getScore() >= 100) {
                user.setRate(100);
            } else {
                user.setRate(user.getRate() + rate.getScore());
            }
            rate.setNowScore(user.getRate());
            rateMapper.insertSelective(rate);
            userMapper.updateByPrimaryKeySelective(user);
        }
        return ResponseDTO.successByMsg(true, "删除成功！");
    }

    /**
     * 获取全部告警数据
     * @param warnDTO
     * @return
     */
    @Override
    public ResponseDTO<List<WarnDTO>> getAllWarn(WarnDTO warnDTO) {
        WarnExample warnExample = new WarnExample();
        WarnExample.Criteria criteria = warnExample.createCriteria();
        if(!CommonUtil.isEmpty(warnDTO.getUserId())) {
            criteria.andUserIdEqualTo(warnDTO.getUserId());
        }
        List<Warn> warnList = warnMapper.selectByExample(warnExample);
        return ResponseDTO.success(CopyUtil.copyList(warnList, WarnDTO.class));
    }
}
