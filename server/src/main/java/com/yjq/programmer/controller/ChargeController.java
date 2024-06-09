package com.yjq.programmer.controller;

import com.yjq.programmer.dto.ChargeDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.IChargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wenLiu
 * @create 2023-12-30 12:46
 */
@RestController
@RequestMapping("/charge")
@Api(tags = "充电桩管理")
public class ChargeController {

    @Resource
    private IChargeService chargeService;

    /**
     * 分页获取充电桩数据
     * @param pageDTO
     * @return
     */
    @ApiOperation("分页获取充电桩数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -3000, message = "电站信息添加失败，请联系管理员！"),
            @ApiResponse(code = -3001, message = "该电站不存在！"),
            @ApiResponse(code = -3002, message = "电站信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -3003, message = "电站信息删除失败，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/list")
    public ResponseDTO<PageDTO<ChargeDTO>> getChargeList(@RequestBody PageDTO<ChargeDTO> pageDTO){
        return chargeService.getChargeList(pageDTO);
    }

    /**
     * 删除充电桩信息
     * @param chargeDTO
     * @return
     */
    @ApiOperation("删除充电桩信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -3000, message = "电站信息添加失败，请联系管理员！"),
            @ApiResponse(code = -3001, message = "该电站不存在！"),
            @ApiResponse(code = -3002, message = "电站信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -3003, message = "电站信息删除失败，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteCharge(@RequestBody ChargeDTO chargeDTO){
        return chargeService.deleteCharge(chargeDTO);
    }

    /**
     * 保存充电桩信息
     * @param chargeDTO
     * @return
     */
    @ApiOperation("保存充电桩信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -3000, message = "电站信息添加失败，请联系管理员！"),
            @ApiResponse(code = -3001, message = "该电站不存在！"),
            @ApiResponse(code = -3002, message = "电站信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -3003, message = "电站信息删除失败，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveCharge(@RequestBody ChargeDTO chargeDTO){
        return chargeService.saveCharge(chargeDTO);
    }

    /**
     * 获取全部充电桩信息
     * @return
     */
    @ApiOperation("获取全部充电桩信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -3000, message = "电站信息添加失败，请联系管理员！"),
            @ApiResponse(code = -3001, message = "该电站不存在！"),
            @ApiResponse(code = -3002, message = "电站信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -3003, message = "电站信息删除失败，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/all")
    public ResponseDTO<List<ChargeDTO>> getAllCharge(){
        return chargeService.getAllCharge();
    }

}
