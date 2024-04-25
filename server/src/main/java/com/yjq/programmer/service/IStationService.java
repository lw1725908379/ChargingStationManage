package com.yjq.programmer.service;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.StationDTO;

import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2023-12-30 10:40
 */
public interface IStationService {

    // 分页获取电站数据
    ResponseDTO<PageDTO<StationDTO>> getStationList(PageDTO<StationDTO> pageDTO);

    // 保存电站信息
    ResponseDTO<Boolean> saveStation(StationDTO stationDTO);

    // 删除电站信息
    ResponseDTO<Boolean> deleteStation(StationDTO stationDTO);

    // 获取全部电站信息
    ResponseDTO<List<StationDTO>> getAllStation();
}
