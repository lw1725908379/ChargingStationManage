package com.yjq.programmer.controller;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.RepairDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.IRepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wenLiu
 * @create 2023-12-31 10:17
 */
@RestController
@RequestMapping("/repair")
@Api(tags = "报修管理")
public class RepairController {

    @Resource
    private IRepairService repairService;

    /**
     * 分页获取报修数据
     * @param pageDTO
     * @return
     */
    @ApiOperation("获取报修列表")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = PageDTO.class),
            @ApiResponse(code = -7000, message = "报修信息创建失败，请联系管理员！"),
            @ApiResponse(code = -7001, message = "报修信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -7002, message = "报修信息删除失败，请联系管理员！"),
            @ApiResponse(code = -7003, message = "此充电桩已经在报修中，请勿重复操作！"),
            @ApiResponse(code = -7004, message = "此充电桩已有人报修，请勿重复操作！")
    })
    @PostMapping("/list")
    public ResponseDTO<PageDTO<RepairDTO>> getRepairList(@RequestBody PageDTO<RepairDTO> pageDTO){
        return repairService.getRepairList(pageDTO);
    }

    /**
     * 删除报修信息
     * @param repairDTO
     * @return
     */
    @ApiOperation("删除报修信息")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = PageDTO.class),
            @ApiResponse(code = -7000, message = "报修信息创建失败，请联系管理员！"),
            @ApiResponse(code = -7001, message = "报修信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -7002, message = "报修信息删除失败，请联系管理员！"),
            @ApiResponse(code = -7003, message = "此充电桩已经在报修中，请勿重复操作！"),
            @ApiResponse(code = -7004, message = "此充电桩已有人报修，请勿重复操作！")
    })
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteRepair(@RequestBody RepairDTO repairDTO) {
        return repairService.deleteRepair(repairDTO);
    }

    /**
     * 保存报修信息
     * @param repairDTO
     * @return
     */
    @ApiOperation("保存报修信息")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = PageDTO.class),
            @ApiResponse(code = -7000, message = "报修信息创建失败，请联系管理员！"),
            @ApiResponse(code = -7001, message = "报修信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -7002, message = "报修信息删除失败，请联系管理员！"),
            @ApiResponse(code = -7003, message = "此充电桩已经在报修中，请勿重复操作！"),
            @ApiResponse(code = -7004, message = "此充电桩已有人报修，请勿重复操作！")
    })
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveRepair(@RequestBody RepairDTO repairDTO){
        return repairService.saveRepair(repairDTO);
    }
}
