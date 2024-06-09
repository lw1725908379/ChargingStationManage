package com.yjq.programmer.controller;

import com.yjq.programmer.dto.OperatorDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.IOperatorService;
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
 * @create 2023-12-30 9:27
 */
@RestController
@RequestMapping("/operator")
@Api(tags = "运营商管理")
public class OperatorController {

    @Resource
    private IOperatorService operatorService;

    /**
     * 分页获取运营商数据
     * @param pageDTO
     * @return
     */
    @ApiOperation("分页获取运营商数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -2000, message = "运营商信息添加失败，请联系管理员！"),
            @ApiResponse(code = -2001, message = "该运营商不存在！"),
            @ApiResponse(code = -2002, message = "运营商信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -2003, message = "运营商信息删除失败，请联系管理员！"),
            @ApiResponse(code = -2004, message = "运营商名称重复，请换一个！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/list")
    public ResponseDTO<PageDTO<OperatorDTO>> getOperatorList(@RequestBody PageDTO<OperatorDTO> pageDTO){
        return operatorService.getOperatorList(pageDTO);
    }

    /**
     * 删除运营商信息
     * @param operatorDTO
     * @return
     */
    @ApiOperation("删除运营商信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -2000, message = "运营商信息添加失败，请联系管理员！"),
            @ApiResponse(code = -2001, message = "该运营商不存在！"),
            @ApiResponse(code = -2002, message = "运营商信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -2003, message = "运营商信息删除失败，请联系管理员！"),
            @ApiResponse(code = -2004, message = "运营商名称重复，请换一个！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteOperator(@RequestBody OperatorDTO operatorDTO){
        return operatorService.deleteOperator(operatorDTO);
    }

    /**
     * 保存运营商信息
     * @param operatorDTO
     * @return
     */
    @ApiOperation("保存运营商信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -2000, message = "运营商信息添加失败，请联系管理员！"),
            @ApiResponse(code = -2001, message = "该运营商不存在！"),
            @ApiResponse(code = -2002, message = "运营商信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -2003, message = "运营商信息删除失败，请联系管理员！"),
            @ApiResponse(code = -2004, message = "运营商名称重复，请换一个！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveOperator(@RequestBody OperatorDTO operatorDTO){
        return operatorService.saveOperator(operatorDTO);
    }

    /**
     * 获取全部运营商信息
     * @return
     */
    @ApiOperation("获取全部运营商信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -2000, message = "运营商信息添加失败，请联系管理员！"),
            @ApiResponse(code = -2001, message = "该运营商不存在！"),
            @ApiResponse(code = -2002, message = "运营商信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -2003, message = "运营商信息删除失败，请联系管理员！"),
            @ApiResponse(code = -2004, message = "运营商名称重复，请换一个！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/all")
    public ResponseDTO<List<OperatorDTO>> getAllOperator(){
        return operatorService.getAllOperator();
    }
}
