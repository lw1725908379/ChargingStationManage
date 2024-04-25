package com.yjq.programmer.controller;

import com.yjq.programmer.dto.ChargeDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.IChargeService;
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
 * @create 2023-12-30 12:46
 */
@RestController
@RequestMapping("/charge")
public class ChargeController {

    @Resource
    private IChargeService chargeService;

    /**
     * 分页获取充电桩数据
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDTO<PageDTO<ChargeDTO>> getChargeList(@RequestBody PageDTO<ChargeDTO> pageDTO){
        return chargeService.getChargeList(pageDTO);
    }

    /**
     * 删除充电桩信息
     * @param chargeDTO
     * @return
     */
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteCharge(@RequestBody ChargeDTO chargeDTO){
        return chargeService.deleteCharge(chargeDTO);
    }

    /**
     * 保存充电桩信息
     * @param chargeDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveCharge(@RequestBody ChargeDTO chargeDTO){
        return chargeService.saveCharge(chargeDTO);
    }

    /**
     * 获取全部充电桩信息
     * @return
     */
    @PostMapping("/all")
    public ResponseDTO<List<ChargeDTO>> getAllCharge(){
        return chargeService.getAllCharge();
    }

}
