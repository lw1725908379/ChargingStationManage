package com.yjq.programmer.controller;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.RepairDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.IRepairService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2023-12-31 10:17
 */
@RestController
@RequestMapping("/repair")
public class RepairController {

    @Resource
    private IRepairService repairService;

    /**
     * 分页获取报修数据
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDTO<PageDTO<RepairDTO>> getRepairList(@RequestBody PageDTO<RepairDTO> pageDTO){
        return repairService.getRepairList(pageDTO);
    }

    /**
     * 删除报修信息
     * @param repairDTO
     * @return
     */
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteRepair(@RequestBody RepairDTO repairDTO) {
        return repairService.deleteRepair(repairDTO);
    }

    /**
     * 保存报修信息
     * @param repairDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveRepair(@RequestBody RepairDTO repairDTO){
        return repairService.saveRepair(repairDTO);
    }
}
