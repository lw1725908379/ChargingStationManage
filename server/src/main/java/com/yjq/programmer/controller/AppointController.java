package com.yjq.programmer.controller;

import com.yjq.programmer.dto.AppointDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.IAppointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author wenLiu
 * @create 2023-12-30 17:17
 */
@RestController
@RequestMapping("/appoint")
@Api(tags = "预约管理")
public class AppointController {

    @Resource
    private IAppointService appointService;

    /**
     * 保存预约信息
     * @param appointDTO
     * @return
     */
    @ApiOperation("保存预约信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -5000, message = "预约失败，请联系管理员！"),
            @ApiResponse(code = -5001, message = "此预约日期时间段已被人预约，请换一个！"),
            @ApiResponse(code = -5002, message = "预约信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -5003, message = "此充电桩正在维修，不能预约！"),
            @ApiResponse(code = -5004, message = "预约信息删除失败，请联系管理员！"),
            @ApiResponse(code = -5005, message = "信誉积分小于80分无法预约，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveAppoint(@RequestBody AppointDTO appointDTO) {
        return appointService.saveAppoint(appointDTO);
    }

    /**
     * 获取已预约的日期
     * @param appointDTO
     * @return
     */
    @ApiOperation("获取已预约的日期")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -5000, message = "预约失败，请联系管理员！"),
            @ApiResponse(code = -5001, message = "此预约日期时间段已被人预约，请换一个！"),
            @ApiResponse(code = -5002, message = "预约信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -5003, message = "此充电桩正在维修，不能预约！"),
            @ApiResponse(code = -5004, message = "预约信息删除失败，请联系管理员！"),
            @ApiResponse(code = -5005, message = "信誉积分小于80分无法预约，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/appointed")
    public ResponseDTO<List<AppointDTO>> getAppointDate(@RequestBody AppointDTO appointDTO) {
        return appointService.getAppointDate(appointDTO);
    }

    /**
     * 分页获取预约数据
     * @param pageDTO
     * @return
     */
    @ApiOperation("分页获取预约数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -5000, message = "预约失败，请联系管理员！"),
            @ApiResponse(code = -5001, message = "此预约日期时间段已被人预约，请换一个！"),
            @ApiResponse(code = -5002, message = "预约信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -5003, message = "此充电桩正在维修，不能预约！"),
            @ApiResponse(code = -5004, message = "预约信息删除失败，请联系管理员！"),
            @ApiResponse(code = -5005, message = "信誉积分小于80分无法预约，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/list")
    public ResponseDTO<PageDTO<AppointDTO>> getAppointList(@RequestBody PageDTO<AppointDTO> pageDTO){
        return appointService.getAppointList(pageDTO);
    }


    /**
     * 更改预约状态信息
     * @param appointDTO
     * @return
     */
    @ApiOperation("更改预约状态信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -5000, message = "预约失败，请联系管理员！"),
            @ApiResponse(code = -5001, message = "此预约日期时间段已被人预约，请换一个！"),
            @ApiResponse(code = -5002, message = "预约信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -5003, message = "此充电桩正在维修，不能预约！"),
            @ApiResponse(code = -5004, message = "预约信息删除失败，请联系管理员！"),
            @ApiResponse(code = -5005, message = "信誉积分小于80分无法预约，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/edit_state")
    public ResponseDTO<BigDecimal> updateAppointState(@RequestBody AppointDTO appointDTO) {
        return appointService.updateAppointState(appointDTO);
    }

    /**
     * 删除预约信息
     * @param appointDTO
     * @return
     */
    @ApiOperation("删除预约信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -5000, message = "预约失败，请联系管理员！"),
            @ApiResponse(code = -5001, message = "此预约日期时间段已被人预约，请换一个！"),
            @ApiResponse(code = -5002, message = "预约信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -5003, message = "此充电桩正在维修，不能预约！"),
            @ApiResponse(code = -5004, message = "预约信息删除失败，请联系管理员！"),
            @ApiResponse(code = -5005, message = "信誉积分小于80分无法预约，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteAppoint(@RequestBody AppointDTO appointDTO) {
        return appointService.deleteAppoint(appointDTO);
    }

    /**
     * 获取全部预约数据
     * @param appointDTO
     * @return
     */
    @ApiOperation("获取全部预约数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -5000, message = "预约失败，请联系管理员！"),
            @ApiResponse(code = -5001, message = "此预约日期时间段已被人预约，请换一个！"),
            @ApiResponse(code = -5002, message = "预约信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -5003, message = "此充电桩正在维修，不能预约！"),
            @ApiResponse(code = -5004, message = "预约信息删除失败，请联系管理员！"),
            @ApiResponse(code = -5005, message = "信誉积分小于80分无法预约，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/all")
    public ResponseDTO<List<AppointDTO>> getAllAppoint(@RequestBody AppointDTO appointDTO) {
        return appointService.getAllAppoint(appointDTO);
    }

}
