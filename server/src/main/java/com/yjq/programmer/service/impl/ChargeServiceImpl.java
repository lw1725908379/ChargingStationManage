package com.yjq.programmer.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.AppointMapper;
import com.yjq.programmer.dao.ChargeMapper;
import com.yjq.programmer.dao.OrderMapper;
import com.yjq.programmer.dao.StationMapper;
import com.yjq.programmer.domain.*;
import com.yjq.programmer.dto.ChargeDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.StationDTO;
import com.yjq.programmer.enums.AppointStateEnum;
import com.yjq.programmer.enums.ChargeStateEnum;
import com.yjq.programmer.service.IChargeService;
import com.yjq.programmer.utils.CommonUtil;
import com.yjq.programmer.utils.CopyUtil;
import com.yjq.programmer.utils.UuidUtil;
import com.yjq.programmer.utils.ValidateEntityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2023-12-30 12:28
 */
@Service
@Transactional
public class ChargeServiceImpl implements IChargeService {

    @Resource
    private ChargeMapper chargeMapper;

    @Resource
    private StationMapper stationMapper;

    @Resource
    private AppointMapper appointMapper;

    @Resource
    private OrderMapper orderMapper;

    /**
     * 分页获取充电桩数据
     * @param pageDTO
     * @return
     */
    @Override
    public ResponseDTO<PageDTO<ChargeDTO>> getChargeList(PageDTO<ChargeDTO> pageDTO) {
        // 更新下最新预约数据
        List<Appoint> appointList = appointMapper.selectByExample(new AppointExample());
        for(Appoint appoint : appointList) {
            // 当前时间超过结束时间，且状态为待使用的预约，状态自动改为已取消
            // 当前时间超过结束时间，且状态为充电中的预约，状态自动改为已完成
            String[] splitTime = appoint.getTime().split("-");
            Date endDate = CommonUtil.getFormatterDate(CommonUtil.getFormatterDate(appoint.getDay(), "yyyy-MM-dd") + " " + splitTime[1], "yyyy-MM-dd HH:mm");
            if(new Date().getTime() > endDate.getTime()) {
                if(AppointStateEnum.WAIT.getCode().equals(appoint.getState())) {
                    appoint.setState(AppointStateEnum.CANCEL.getCode());
                } else if(AppointStateEnum.USE.getCode().equals(appoint.getState())) {
                    appoint.setState(AppointStateEnum.OVER.getCode());
                    appoint.setEndTime(endDate);
                    Charge charge = chargeMapper.selectByPrimaryKey(appoint.getChargeId());
                    BigDecimal totalPrice = charge.getPrice().multiply(new BigDecimal(DateUtil.between(appoint.getStartTime(), endDate, DateUnit.MINUTE)));
                    Order order = new Order();
                    order.setId(UuidUtil.getShortUuid());
                    order.setCreateTime(new Date());
                    order.setUserId(appoint.getUserId());
                    order.setChargeId(appoint.getChargeId());
                    order.setChargeName(appoint.getChargeName());
                    order.setTotalPrice(totalPrice);
                    if(orderMapper.insertSelective(order) == 0) {
                        return ResponseDTO.errorByMsg(CodeMsg.ORDER_ADD_ERROR);
                    }
                }
                appointMapper.updateByPrimaryKeySelective(appoint);
            }
        }

        // 查询充电桩数据
        ChargeExample chargeExample = new ChargeExample();
        // 不知道当前页多少，默认为第一页
        if(pageDTO.getPage() == null){
            pageDTO.setPage(1);
        }
        // 不知道每页多少条记录，默认为每页5条记录
        if(pageDTO.getSize() == null){
            pageDTO.setSize(5);
        }
        ChargeExample.Criteria c1 = chargeExample.createCriteria();
        if(pageDTO.getParam() != null) {
            ChargeDTO chargeDTO = pageDTO.getParam();
            c1.andNameLike("%" + chargeDTO.getName() + "%");
            if(chargeDTO.getState() != null && chargeDTO.getState() != 0) {
                c1.andStateEqualTo(chargeDTO.getState());
            }
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getSize());
        // 分页查出充电桩数据
        List<Charge> chargeList = chargeMapper.selectByExample(chargeExample);
        PageInfo<Charge> pageInfo = new PageInfo<>(chargeList);
        // 获取数据的总数
        pageDTO.setTotal(pageInfo.getTotal());
        // 将domain类型数据  转成 DTO类型数据
        List<ChargeDTO> chargeDTOList = CopyUtil.copyList(chargeList, ChargeDTO.class);
        for(ChargeDTO chargeDTO : chargeDTOList) {
            Station station = stationMapper.selectByPrimaryKey(chargeDTO.getStationId());
            chargeDTO.setStationDTO(CopyUtil.copy(station, StationDTO.class));
        }
        pageDTO.setList(chargeDTOList);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * 保存充电桩信息
     * @param chargeDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> saveCharge(ChargeDTO chargeDTO) {
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(chargeDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseDTO.errorByMsg(validate);
        }
        Charge charge = CopyUtil.copy(chargeDTO, Charge.class);
        if(CommonUtil.isEmpty(charge.getId())) {
            // 添加操作
            charge.setId(UuidUtil.getShortUuid());
            Station station = stationMapper.selectByPrimaryKey(charge.getStationId());
            station.setChargeNum(station.getChargeNum() + 1);
            if(ChargeStateEnum.NORMAL.getCode().equals(charge.getState())) {
                station.setAvailableChargeNum(station.getAvailableChargeNum() + 1);
            }
            if(chargeMapper.insertSelective(charge) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.CHARGE_ADD_ERROR);
            }
            // 更新充电桩数据
            stationMapper.updateByPrimaryKeySelective(station);
        } else {
            Charge chargeDB = chargeMapper.selectByPrimaryKey(charge.getId());
            Station stationOld = stationMapper.selectByPrimaryKey(chargeDB.getStationId());
            Station stationNew = stationMapper.selectByPrimaryKey(charge.getStationId());
            stationOld.setChargeNum(stationOld.getChargeNum() - 1);
            stationNew.setChargeNum(stationNew.getChargeNum() + 1);
            if(ChargeStateEnum.NORMAL.getCode().equals(chargeDB.getState())) {
                stationOld.setAvailableChargeNum(stationOld.getAvailableChargeNum() - 1);
            }
            if(ChargeStateEnum.NORMAL.getCode().equals(charge.getState())) {
                stationNew.setAvailableChargeNum(stationNew.getAvailableChargeNum() + 1);
            }

            // 修改操作
            if(chargeMapper.updateByPrimaryKeySelective(charge) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.CHARGE_EDIT_ERROR);
            }
            // 更新充电桩数据
            stationMapper.updateByPrimaryKeySelective(stationOld);
            stationMapper.updateByPrimaryKeySelective(stationNew);
        }
        return ResponseDTO.successByMsg(true, "保存成功！");
    }

    /**
     * 删除充电桩信息
     * @param chargeDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> deleteCharge(ChargeDTO chargeDTO) {
        if(CommonUtil.isEmpty(chargeDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        Charge charge = chargeMapper.selectByPrimaryKey(chargeDTO.getId());
        // 删除充电桩信息
        if(chargeMapper.deleteByPrimaryKey(chargeDTO.getId()) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.CHARGE_DELETE_ERROR);
        }
        // 更新电站信息
        Station station = stationMapper.selectByPrimaryKey(charge.getStationId());
        station.setChargeNum(station.getChargeNum() - 1);
        if(ChargeStateEnum.NORMAL.getCode().equals(charge.getState())) {
            station.setAvailableChargeNum(station.getAvailableChargeNum() - 1);
        }
        stationMapper.updateByPrimaryKeySelective(station);
        return ResponseDTO.successByMsg(true, "删除成功！");
    }

    /**
     * 获取全部充电桩信息
     * @return
     */
    @Override
    public ResponseDTO<List<ChargeDTO>> getAllCharge() {
        ChargeExample chargeExample = new ChargeExample();
        List<Charge> chargeList = chargeMapper.selectByExample(chargeExample);
        return ResponseDTO.success(CopyUtil.copyList(chargeList, ChargeDTO.class));
    }
}
