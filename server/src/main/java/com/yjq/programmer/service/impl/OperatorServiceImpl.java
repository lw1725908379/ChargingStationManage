package com.yjq.programmer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.OperatorMapper;
import com.yjq.programmer.dao.StationMapper;
import com.yjq.programmer.domain.Operator;
import com.yjq.programmer.domain.OperatorExample;
import com.yjq.programmer.domain.Station;
import com.yjq.programmer.domain.StationExample;
import com.yjq.programmer.dto.OperatorDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.StationDTO;
import com.yjq.programmer.service.IOperatorService;
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
 * @create 2023-12-30 9:27
 */
@Service
@Transactional
public class OperatorServiceImpl implements IOperatorService {

    @Resource
    private OperatorMapper operatorMapper;

    @Resource
    private IStationService stationService;

    @Resource
    private StationMapper stationMapper;

    /**
     * 分页获取运营商数据
     * @param pageDTO
     * @return
     */
    @Override
    public ResponseDTO<PageDTO<OperatorDTO>> getOperatorList(PageDTO<OperatorDTO> pageDTO) {
        OperatorExample operatorExample = new OperatorExample();
        // 不知道当前页多少，默认为第一页
        if(pageDTO.getPage() == null){
            pageDTO.setPage(1);
        }
        // 不知道每页多少条记录，默认为每页5条记录
        if(pageDTO.getSize() == null){
            pageDTO.setSize(5);
        }
        OperatorExample.Criteria c1 = operatorExample.createCriteria();
        if(pageDTO.getParam() != null) {
            OperatorDTO operatorDTO = pageDTO.getParam();
            c1.andNameLike("%" + operatorDTO.getName() + "%");
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getSize());
        // 分页查出运营商数据
        List<Operator> operatorList = operatorMapper.selectByExample(operatorExample);
        PageInfo<Operator> pageInfo = new PageInfo<>(operatorList);
        // 获取数据的总数
        pageDTO.setTotal(pageInfo.getTotal());
        // 将domain类型数据  转成 DTO类型数据
        List<OperatorDTO> operatorDTOList = CopyUtil.copyList(operatorList, OperatorDTO.class);
        pageDTO.setList(operatorDTOList);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * 保存运营商信息
     * @param operatorDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> saveOperator(OperatorDTO operatorDTO) {
        // 进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(operatorDTO);
        if (!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseDTO.errorByMsg(validate);
        }
        Operator operator = CopyUtil.copy(operatorDTO, Operator.class);
        if(CommonUtil.isEmpty(operator.getId())) {
            // 添加操作
            // 判断运营商名称是否存在
            if(isNameExist(operator, "")){
                return ResponseDTO.errorByMsg(CodeMsg.OPERATOR_NAME_EXIST);
            }
            operator.setId(UuidUtil.getShortUuid());
            if(operatorMapper.insertSelective(operator) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.OPERATOR_ADD_ERROR);
            }
        } else {
            // 修改操作
            // 判断运营商名称是否存在
            if(isNameExist(operator, operator.getId())){
                return ResponseDTO.errorByMsg(CodeMsg.OPERATOR_NAME_EXIST);
            }
            if(operatorMapper.updateByPrimaryKeySelective(operator) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.OPERATOR_EDIT_ERROR);
            }
        }
        return ResponseDTO.successByMsg(true, "保存成功！");
    }

    /**
     * 删除运营商信息
     * @param operatorDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> deleteOperator(OperatorDTO operatorDTO) {
        if(CommonUtil.isEmpty(operatorDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        // 删除运营商信息
        if(operatorMapper.deleteByPrimaryKey(operatorDTO.getId()) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.OPERATOR_DELETE_ERROR);
        }
        // 删除该运营商下所有电站信息
        StationExample stationExample = new StationExample();
        stationExample.createCriteria().andOperatorIdEqualTo(operatorDTO.getId());
        List<Station> stationList = stationMapper.selectByExample(stationExample);
        for(Station station : stationList) {
            stationService.deleteStation(CopyUtil.copy(station, StationDTO.class));
        }
        return ResponseDTO.successByMsg(true, "删除成功！");
    }

    /**
     * 获取全部运营商信息
     * @return
     */
    @Override
    public ResponseDTO<List<OperatorDTO>> getAllOperator() {
        OperatorExample operatorExample = new OperatorExample();
        List<Operator> operatorList = operatorMapper.selectByExample(operatorExample);
        return ResponseDTO.success(CopyUtil.copyList(operatorList, OperatorDTO.class));
    }

    /**
     * 判断运营商名称是否重复
     * @param operator
     * @param id
     * @return
     */
    public Boolean isNameExist(Operator operator, String id) {
        OperatorExample operatorExample = new OperatorExample();
        operatorExample.createCriteria().andNameEqualTo(operator.getName());
        List<Operator> selectedOperatorList = operatorMapper.selectByExample(operatorExample);
        if (selectedOperatorList != null && selectedOperatorList.size() > 0) {
            if (selectedOperatorList.size() > 1) {
                return true; //出现重复
            }
            if (!selectedOperatorList.get(0).getId().equals(id)) {
                return true; //出现重复
            }
        }
        return false;//没有重复
    }
}
