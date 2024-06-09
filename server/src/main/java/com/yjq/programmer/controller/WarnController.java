package com.yjq.programmer.controller;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.WarnDTO;
import com.yjq.programmer.service.IWarnService;
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
public class WarnController {

    @Resource
    private IWarnService warnService;

    /**
     * 分页获取告警数据
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDTO<PageDTO<WarnDTO>> getWarnList(@RequestBody PageDTO<WarnDTO> pageDTO){
        return warnService.getWarnList(pageDTO);
    }

    /**
     * 删除告警信息
     * @param warnDTO
     * @return
     */
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteWarn(@RequestBody WarnDTO warnDTO){
        return warnService.deleteWarn(warnDTO);
    }

    /**
     * 保存告警信息
     * @param warnDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveWarn(@RequestBody WarnDTO warnDTO){
        return warnService.saveWarn(warnDTO);
    }

    /**
     * 获取全部告警数据
     * @param warnDTO
     * @return
     */
    @PostMapping("/all")
    public ResponseDTO<List<WarnDTO>> getAllWarn(@RequestBody WarnDTO warnDTO) {
        return warnService.getAllWarn(warnDTO);
    }

}
