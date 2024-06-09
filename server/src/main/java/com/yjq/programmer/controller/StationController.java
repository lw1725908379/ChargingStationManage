package com.yjq.programmer.controller;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.StationDTO;
import com.yjq.programmer.service.IStationService;
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
 * @create 2023-12-30 10:47
 */
@RestController
@RequestMapping("/station")
@Api(tags = "电站管理")
public class StationController {

    @Resource
    private IStationService stationService;

    /**
     * 分页获取电站数据
     * @param pageDTO
     * @return
     */
    @ApiOperation("获取电站列表")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = PageDTO.class),
            @ApiResponse(code = -3000, message = "电站信息添加失败，请联系管理员！"),
            @ApiResponse(code = -3001, message = "该电站不存在！"),
            @ApiResponse(code = -3002, message = "电站信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -3003, message = "电站信息删除失败，请联系管理员！")
    })
    @PostMapping("/list")
    public ResponseDTO<PageDTO<StationDTO>> getStationList(@RequestBody PageDTO<StationDTO> pageDTO){
        return stationService.getStationList(pageDTO);
    }

    /**
     * 删除电站信息
     * @param stationDTO
     * @return
     */
    @ApiOperation("删除电站信息")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = PageDTO.class),
            @ApiResponse(code = -3000, message = "电站信息添加失败，请联系管理员！"),
            @ApiResponse(code = -3001, message = "该电站不存在！"),
            @ApiResponse(code = -3002, message = "电站信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -3003, message = "电站信息删除失败，请联系管理员！")
    })
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteStation(@RequestBody StationDTO stationDTO){
        return stationService.deleteStation(stationDTO);
    }

    /**
     * 保存电站信息
     * @param stationDTO
     * @return
     */
    @ApiOperation("保存电站信息")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = PageDTO.class),
            @ApiResponse(code = -3000, message = "电站信息添加失败，请联系管理员！"),
            @ApiResponse(code = -3001, message = "该电站不存在！"),
            @ApiResponse(code = -3002, message = "电站信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -3003, message = "电站信息删除失败，请联系管理员！")
    })
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveStation(@RequestBody StationDTO stationDTO){
        return stationService.saveStation(stationDTO);
    }

    /**
     * 获取全部电站信息
     * @return
     */
    @ApiOperation("获取全部电站信息")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功", response = PageDTO.class),
            @ApiResponse(code = -3000, message = "电站信息添加失败，请联系管理员！"),
            @ApiResponse(code = -3001, message = "该电站不存在！"),
            @ApiResponse(code = -3002, message = "电站信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -3003, message = "电站信息删除失败，请联系管理员！")
    })
    @PostMapping("/all")
    public ResponseDTO<List<StationDTO>> getAllStation(){
        return stationService.getAllStation();
    }
}
