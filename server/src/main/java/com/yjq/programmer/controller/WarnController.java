package com.yjq.programmer.controller;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.WarnDTO;
import com.yjq.programmer.service.IWarnService;
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
 * @create 2023-12-31 12:51
 */
@RestController
@RequestMapping("/warn")
@Api(tags = "告警管理")
public class WarnController {

    @Resource
    private IWarnService warnService;

    /**
     * 分页获取告警数据
     * @param pageDTO
     * @return
     */
    @ApiOperation("获取告警列表")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = PageDTO.class)
    })
    @PostMapping("/list")
    public ResponseDTO<PageDTO<WarnDTO>> getWarnList(@RequestBody PageDTO<WarnDTO> pageDTO){
        return warnService.getWarnList(pageDTO);
    }

    /**
     * 删除告警信息
     * @param warnDTO
     * @return
     */
    @ApiOperation("删除告警信息")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = Boolean.class),
            @ApiResponse(code = -9002, message = "告警信息删除失败，请联系管理员！")
    })
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteWarn(@RequestBody WarnDTO warnDTO){
        return warnService.deleteWarn(warnDTO);
    }

    /**
     * 保存告警信息
     * @param warnDTO
     * @return
     */
    @ApiOperation("保存告警信息")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = Boolean.class),
            @ApiResponse(code = -9000, message = "告警信息创建失败，请联系管理员！")
    })
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveWarn(@RequestBody WarnDTO warnDTO){
        return warnService.saveWarn(warnDTO);
    }

    /**
     * 获取全部告警数据
     * @param warnDTO
     * @return
     */
    @ApiOperation("获取全部告警数据")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = List.class)
    })
    @PostMapping("/all")
    public ResponseDTO<List<WarnDTO>> getAllWarn(@RequestBody WarnDTO warnDTO) {
        return warnService.getAllWarn(warnDTO);
    }

}
