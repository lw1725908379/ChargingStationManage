package com.yjq.programmer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.ChargeMapper;
import com.yjq.programmer.dao.OperatorMapper;
import com.yjq.programmer.dao.StationMapper;
import com.yjq.programmer.domain.ChargeExample;
import com.yjq.programmer.domain.Operator;
import com.yjq.programmer.domain.Station;
import com.yjq.programmer.domain.StationExample;
import com.yjq.programmer.dto.OperatorDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.StationDTO;
import com.yjq.programmer.service.IStationService;
import com.yjq.programmer.utils.CommonUtil;
import com.yjq.programmer.utils.CopyUtil;
import com.yjq.programmer.utils.UuidUtil;
import com.yjq.programmer.utils.ValidateEntityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2023-12-30 10:40
 */
@Service
@Transactional
public class StationServiceImpl implements IStationService {

    @Resource
    private StationMapper stationMapper;

    @Resource
    private OperatorMapper operatorMapper;

    @Resource
    private ChargeMapper chargeMapper;

    /**
     * 分页获取电站数据
     * @param pageDTO
     * @return
     */
    @Override
    public ResponseDTO<PageDTO<StationDTO>> getStationList(PageDTO<StationDTO> pageDTO) {
        StationExample stationExample = new StationExample();
        // 不知道当前页多少，默认为第一页
        if(pageDTO.getPage() == null){
            pageDTO.setPage(1);
        }
        // 不知道每页多少条记录，默认为每页5条记录
        if(pageDTO.getSize() == null){
            pageDTO.setSize(5);
        }
        StationExample.Criteria c1 = stationExample.createCriteria();
        if(pageDTO.getParam() != null) {
            StationDTO stationDTO = pageDTO.getParam();
            c1.andNameLike("%" + stationDTO.getName() + "%");
            if(!CommonUtil.isEmpty(stationDTO.getOperatorId()) && !"0".equals(stationDTO.getOperatorId())) {
                c1.andOperatorIdEqualTo(stationDTO.getOperatorId());
            }
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getSize());
        // 分页查出电站数据
        List<Station> stationList = stationMapper.selectByExample(stationExample);
        PageInfo<Station> pageInfo = new PageInfo<>(stationList);
        // 获取数据的总数
        pageDTO.setTotal(pageInfo.getTotal());
        // 将domain类型数据  转成 DTO类型数据
        List<StationDTO> stationDTOList = CopyUtil.copyList(stationList, StationDTO.class);
        for(StationDTO stationDTO : stationDTOList) {
            Operator operator = operatorMapper.selectByPrimaryKey(stationDTO.getOperatorId());
            stationDTO.setOperatorDTO(CopyUtil.copy(operator, OperatorDTO.class));
        }
        pageDTO.setList(stationDTOList);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * 保存电站信息
     * @param stationDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> saveStation(StationDTO stationDTO) {
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(stationDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseDTO.errorByMsg(validate);
        }
        Station station = CopyUtil.copy(stationDTO, Station.class);
        if(CommonUtil.isEmpty(station.getId())) {
            // 添加操作
            station.setId(UuidUtil.getShortUuid());
            Operator operator = operatorMapper.selectByPrimaryKey(station.getOperatorId());
            operator.setStationNum(operator.getStationNum() + 1);
            if(stationMapper.insertSelective(station) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.STATION_ADD_ERROR);
            }
            // 更新运营商数据
            operatorMapper.updateByPrimaryKeySelective(operator);
        } else {
            Station stationDB = stationMapper.selectByPrimaryKey(station.getId());
            Operator operatorOld = operatorMapper.selectByPrimaryKey(stationDB.getOperatorId());
            Operator operatorNew = operatorMapper.selectByPrimaryKey(station.getOperatorId());
            operatorOld.setStationNum(operatorOld.getStationNum() - 1);
            operatorNew.setStationNum(operatorNew.getStationNum() + 1);
            // 修改操作
            if(stationMapper.updateByPrimaryKeySelective(station) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.STATION_EDIT_ERROR);
            }
            // 更新运营商数据
            operatorMapper.updateByPrimaryKeySelective(operatorOld);
            operatorMapper.updateByPrimaryKeySelective(operatorNew);
        }
        return ResponseDTO.successByMsg(true, "保存成功！");
    }

    /**
     * 删除电站信息
     * @param stationDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> deleteStation(StationDTO stationDTO) {
        if(CommonUtil.isEmpty(stationDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        Operator operator = operatorMapper.selectByPrimaryKey(stationDTO.getOperatorId());
        // 删除电站信息
        if(stationMapper.deleteByPrimaryKey(stationDTO.getId()) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.STATION_DELETE_ERROR);
        }
        // 删除电站有关的充电桩信息
        ChargeExample chargeExample = new ChargeExample();
        chargeExample.createCriteria().andStationIdEqualTo(stationDTO.getId());
        chargeMapper.deleteByExample(chargeExample);
        // 更新运营商信息
        operator.setStationNum(operator.getStationNum() - 1);
        operatorMapper.updateByPrimaryKeySelective(operator);
        return ResponseDTO.successByMsg(true, "删除成功！");
    }

    /**
     * 获取全部电站信息
     * @return
     */
    @Override
    public ResponseDTO<List<StationDTO>> getAllStation() {
        StationExample stationExample = new StationExample();
        List<Station> stationList = stationMapper.selectByExample(stationExample);
        return ResponseDTO.success(CopyUtil.copyList(stationList, StationDTO.class));
    }
}
