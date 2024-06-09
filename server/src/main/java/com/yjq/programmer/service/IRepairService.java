package com.yjq.programmer.service;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.RepairDTO;
import com.yjq.programmer.dto.ResponseDTO;

/**
 * @author wenLiu
 * @create 2023-12-31 10:08
 */
public interface IRepairService {

    // 分页获取报修数据
    ResponseDTO<PageDTO<RepairDTO>> getRepairList(PageDTO<RepairDTO> pageDTO);

    // 保存报修信息
    ResponseDTO<Boolean> saveRepair(RepairDTO repairDTO);

    // 删除报修信息
    ResponseDTO<Boolean> deleteRepair(RepairDTO repairDTO);
}
