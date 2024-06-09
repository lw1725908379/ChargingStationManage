package com.yjq.programmer.service;

import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.WarnDTO;

import java.util.List;

/**
 * @author wenLiu
 * @create 2023-12-31 12:13
 */
public interface IWarnService {

    // 分页获取告警数据
    ResponseDTO<PageDTO<WarnDTO>> getWarnList(PageDTO<WarnDTO> pageDTO);

    // 保存告警信息
    ResponseDTO<Boolean> saveWarn(WarnDTO warnDTO);

    // 删除告警信息
    ResponseDTO<Boolean> deleteWarn(WarnDTO warnDTO);

    // 获取全部告警数据
    ResponseDTO<List<WarnDTO>> getAllWarn(WarnDTO warnDTO);
}
