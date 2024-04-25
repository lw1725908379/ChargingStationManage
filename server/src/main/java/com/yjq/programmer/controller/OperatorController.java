package com.yjq.programmer.controller;

import com.yjq.programmer.dto.OperatorDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.IOperatorService;
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
 * @create 2023-12-30 9:27
 */
@RestController
@RequestMapping("/operator")
public class OperatorController {

    @Resource
    private IOperatorService operatorService;

    /**
     * 分页获取运营商数据
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDTO<PageDTO<OperatorDTO>> getOperatorList(@RequestBody PageDTO<OperatorDTO> pageDTO){
        return operatorService.getOperatorList(pageDTO);
    }

    /**
     * 删除运营商信息
     * @param operatorDTO
     * @return
     */
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteOperator(@RequestBody OperatorDTO operatorDTO){
        return operatorService.deleteOperator(operatorDTO);
    }

    /**
     * 保存运营商信息
     * @param operatorDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveOperator(@RequestBody OperatorDTO operatorDTO){
        return operatorService.saveOperator(operatorDTO);
    }

    /**
     * 获取全部运营商信息
     * @return
     */
    @PostMapping("/all")
    public ResponseDTO<List<OperatorDTO>> getAllOperator(){
        return operatorService.getAllOperator();
    }
}
