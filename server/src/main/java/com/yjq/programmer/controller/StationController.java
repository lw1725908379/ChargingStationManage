package com.yjq.programmer.controller;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.StationDTO;
import com.yjq.programmer.service.IStationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2023-12-30 10:47
 */
@RestController
@RequestMapping("/station")
public class StationController {

    @Resource
    private IStationService stationService;

    /**
     * 分页获取电站数据
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDTO<PageDTO<StationDTO>> getStationList(@RequestBody PageDTO<StationDTO> pageDTO){
        return stationService.getStationList(pageDTO);
    }

    /**
     * 删除电站信息
     * @param stationDTO
     * @return
     */
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteStation(@RequestBody StationDTO stationDTO){
        return stationService.deleteStation(stationDTO);
    }

    /**
     * 保存电站信息
     * @param stationDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveStation(@RequestBody StationDTO stationDTO){
        return stationService.saveStation(stationDTO);
    }

    /**
     * 获取全部电站信息
     * @return
     */
    @PostMapping("/all")
    public ResponseDTO<List<StationDTO>> getAllStation(){
        return stationService.getAllStation();
    }
}
