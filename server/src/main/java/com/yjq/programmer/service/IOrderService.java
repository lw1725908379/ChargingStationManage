package com.yjq.programmer.service;

import com.yjq.programmer.dto.OrderDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;

import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2023-12-30 22:13
 */
public interface IOrderService {

    // 分页获取订单数据
    ResponseDTO<PageDTO<OrderDTO>> getOrderList(PageDTO<OrderDTO> pageDTO);

    // 修改订单状态
    ResponseDTO<Boolean> editOrderState(OrderDTO orderDTO);

    // 删除订单信息
    ResponseDTO<Boolean> deleteOrder(OrderDTO orderDTO);

    // 获取全部订单数据
    ResponseDTO<List<OrderDTO>> getAllOrder(OrderDTO orderDTO);
}
