package com.yjq.programmer.service;

import com.yjq.programmer.dto.AppointDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wenLiu
 * @create 2023-12-30 17:05
 */
public interface IAppointService {

    // 保存预约信息
    ResponseDTO<Boolean> saveAppoint(AppointDTO appointDTO);

    // 获取已预约的日期
    ResponseDTO<List<AppointDTO>> getAppointDate(AppointDTO appointDTO);

    // 分页获取预约数据
    ResponseDTO<PageDTO<AppointDTO>> getAppointList(PageDTO<AppointDTO> pageDTO);

    // 更改预约状态信息
    ResponseDTO<BigDecimal> updateAppointState(AppointDTO appointDTO);

    // 删除预约信息
    ResponseDTO<Boolean> deleteAppoint(AppointDTO appointDTO);

    // 获取全部预约数据
    ResponseDTO<List<AppointDTO>> getAllAppoint(AppointDTO appointDTO);
}
