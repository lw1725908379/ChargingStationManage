package com.yjq.programmer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.ChargeMapper;
import com.yjq.programmer.dao.RepairMapper;
import com.yjq.programmer.dao.StationMapper;
import com.yjq.programmer.domain.Charge;
import com.yjq.programmer.domain.Repair;
import com.yjq.programmer.domain.RepairExample;
import com.yjq.programmer.domain.Station;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.RepairDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.enums.ChargeStateEnum;
import com.yjq.programmer.enums.RepairStateEnum;
import com.yjq.programmer.service.IRepairService;
import com.yjq.programmer.utils.CommonUtil;
import com.yjq.programmer.utils.CopyUtil;
import com.yjq.programmer.utils.UuidUtil;
import com.yjq.programmer.utils.ValidateEntityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2023-12-31 10:09
 */
@Service
@Transactional
public class RepairServiceImpl implements IRepairService {

    @Resource
    private RepairMapper repairMapper;

    @Resource
    private ChargeMapper chargeMapper;

    @Resource
    private StationMapper stationMapper;

    /**
     * 分页获取报修数据
     * @param pageDTO
     * @return
     */
    @Override
    public ResponseDTO<PageDTO<RepairDTO>> getRepairList(PageDTO<RepairDTO> pageDTO) {
        RepairExample repairExample = new RepairExample();
        // 不知道当前页多少，默认为第一页
        if(pageDTO.getPage() == null){
            pageDTO.setPage(1);
        }
        // 不知道每页多少条记录，默认为每页5条记录
        if(pageDTO.getSize() == null){
            pageDTO.setSize(5);
        }
        RepairExample.Criteria c1 = repairExample.createCriteria();
        if(pageDTO.getParam() != null) {
            RepairDTO repairDTO = pageDTO.getParam();
            if(!CommonUtil.isEmpty(repairDTO.getUserId())) {
                c1.andUserIdEqualTo(repairDTO.getUserId());
            }
            c1.andUsernameLike("%" + repairDTO.getUsername() + "%");
            if(!(CommonUtil.isEmpty(repairDTO.getChargeId())) && !"0".equals(repairDTO.getChargeId())) {
                c1.andChargeIdEqualTo(repairDTO.getChargeId());
            }
            if(repairDTO.getState() != null && repairDTO.getState() != 0) {
                c1.andStateEqualTo(repairDTO.getState());
            }
        }
        repairExample.setOrderByClause("create_time desc");
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getSize());
        // 分页查出报修数据
        List<Repair> repairList = repairMapper.selectByExample(repairExample);
        PageInfo<Repair> pageInfo = new PageInfo<>(repairList);
        // 获取数据的总数
        pageDTO.setTotal(pageInfo.getTotal());
        // 将domain类型数据  转成 DTO类型数据
        List<RepairDTO> repairDTOList = CopyUtil.copyList(repairList, RepairDTO.class);
        pageDTO.setList(repairDTOList);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * 保存报修信息
     * @param repairDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> saveRepair(RepairDTO repairDTO) {
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(repairDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseDTO.errorByMsg(validate);
        }
        Repair repair = CopyUtil.copy(repairDTO, Repair.class);
        Charge charge = chargeMapper.selectByPrimaryKey(repair.getChargeId());
        if(CommonUtil.isEmpty(repair.getId())) {
            if(ChargeStateEnum.REPAIR.getCode().equals(charge.getState())) {
                return ResponseDTO.errorByMsg(CodeMsg.CHARGE_ALREADY_REPAIR);
            }
            RepairExample repairExample = new RepairExample();
            repairExample.createCriteria().andChargeIdEqualTo(charge.getId()).andStateEqualTo(RepairStateEnum.NO.getCode());
            if(repairMapper.selectByExample(repairExample).size() > 0) {
                return ResponseDTO.errorByMsg(CodeMsg.REPAIR_ALREADY_EXIST);
            }
            // 添加操作
            repair.setId(UuidUtil.getShortUuid());
            repair.setCreateTime(new Date());
            if(repairMapper.insertSelective(repair) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.REPAIR_ADD_ERROR);
            }
        } else {
            Repair repairDB = repairMapper.selectByPrimaryKey(repair.getId());
            Station station = stationMapper.selectByPrimaryKey(charge.getStationId());
            if(RepairStateEnum.YES.getCode().equals(repair.getState())) {
                charge.setState(ChargeStateEnum.REPAIR.getCode());
                station.setAvailableChargeNum(station.getAvailableChargeNum() - 1);
            }
            if(!RepairStateEnum.YES.getCode().equals(repair.getState())
                &&
                RepairStateEnum.YES.getCode().equals(repairDB.getState())
            ) {
                charge.setState(ChargeStateEnum.NORMAL.getCode());
                station.setAvailableChargeNum(station.getAvailableChargeNum() + 1);
            }
            if(chargeMapper.updateByPrimaryKeySelective(charge) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.CHARGE_EDIT_ERROR);
            }
            // 修改操作
            if(repairMapper.updateByPrimaryKeySelective(repair) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.REPAIR_EDIT_ERROR);
            }
            stationMapper.updateByPrimaryKeySelective(station);
        }
        return ResponseDTO.successByMsg(true, "保存成功！");
    }

    /**
     * 删除报修信息
     * @param repairDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> deleteRepair(RepairDTO repairDTO) {
        if(CommonUtil.isEmpty(repairDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        if(repairMapper.deleteByPrimaryKey(repairDTO.getId()) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.REPAIR_DELETE_ERROR);
        }
        return ResponseDTO.successByMsg(true, "删除成功！");
    }
}
