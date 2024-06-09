package com.yjq.programmer.controller;

import com.yjq.programmer.dto.AppointDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.IAppointService;
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
public class AppointController {

    @Resource
    private IAppointService appointService;

    /**
     * 保存预约信息
     * @param appointDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveAppoint(@RequestBody AppointDTO appointDTO) {
        return appointService.saveAppoint(appointDTO);
    }

    /**
     * 获取已预约的日期
     * @param appointDTO
     * @return
     */
    @PostMapping("/appointed")
    public ResponseDTO<List<AppointDTO>> getAppointDate(@RequestBody AppointDTO appointDTO) {
        return appointService.getAppointDate(appointDTO);
    }

    /**
     * 分页获取预约数据
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDTO<PageDTO<AppointDTO>> getAppointList(@RequestBody PageDTO<AppointDTO> pageDTO){
        return appointService.getAppointList(pageDTO);
    }


    /**
     * 更改预约状态信息
     * @param appointDTO
     * @return
     */
    @PostMapping("/edit_state")
    public ResponseDTO<BigDecimal> updateAppointState(@RequestBody AppointDTO appointDTO) {
        return appointService.updateAppointState(appointDTO);
    }

    /**
     * 删除预约信息
     * @param appointDTO
     * @return
     */
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteAppoint(@RequestBody AppointDTO appointDTO) {
        return appointService.deleteAppoint(appointDTO);
    }

    /**
     * 获取全部预约数据
     * @param appointDTO
     * @return
     */
    @PostMapping("/all")
    public ResponseDTO<List<AppointDTO>> getAllAppoint(@RequestBody AppointDTO appointDTO) {
        return appointService.getAllAppoint(appointDTO);
    }

}
