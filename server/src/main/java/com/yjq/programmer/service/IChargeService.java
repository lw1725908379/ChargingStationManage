package com.yjq.programmer.service;

import com.yjq.programmer.dto.ChargeDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;

import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2023-12-30 12:28
 */
public interface IChargeService {

    // 分页获取充电桩数据
    ResponseDTO<PageDTO<ChargeDTO>> getChargeList(PageDTO<ChargeDTO> pageDTO);

    // 保存充电桩信息
    ResponseDTO<Boolean> saveCharge(ChargeDTO chargeDTO);

    // 删除充电桩信息
    ResponseDTO<Boolean> deleteCharge(ChargeDTO chargeDTO);

    // 获取全部充电桩信息
    ResponseDTO<List<ChargeDTO>> getAllCharge();
}
