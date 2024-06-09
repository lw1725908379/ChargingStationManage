package com.yjq.programmer.service;

import com.yjq.programmer.dto.OperatorDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;

import java.util.List;

/**
 * @author wenLiu
 * @create 2023-12-30 9:27
 */
public interface IOperatorService {

    // 分页获取运营商数据
    ResponseDTO<PageDTO<OperatorDTO>> getOperatorList(PageDTO<OperatorDTO> pageDTO);

    // 保存运营商信息
    ResponseDTO<Boolean> saveOperator(OperatorDTO operatorDTO);

    // 删除运营商信息
    ResponseDTO<Boolean> deleteOperator(OperatorDTO operatorDTO);

    // 获取全部运营商信息
    ResponseDTO<List<OperatorDTO>> getAllOperator();
}
