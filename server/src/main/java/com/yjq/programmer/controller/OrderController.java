package com.yjq.programmer.controller;

import com.yjq.programmer.dto.OrderDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.IOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wenLiu
 * @create 2023-12-30 22:13
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    /**
     * 分页获取订单数据
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDTO<PageDTO<OrderDTO>> getOrderList(@RequestBody PageDTO<OrderDTO> pageDTO){
        return orderService.getOrderList(pageDTO);
    }

    /**
     * 更改订单状态信息
     * @param orderDTO
     * @return
     */
    @PostMapping("/edit_state")
    public ResponseDTO<Boolean> editOrderState(@RequestBody OrderDTO orderDTO) {
        return orderService.editOrderState(orderDTO);
    }

    /**
     * 删除订单信息
     * @param orderDTO
     * @return
     */
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.deleteOrder(orderDTO);
    }

    /**
     * 获取全部订单数据
     * @param orderDTO
     * @return
     */
    @PostMapping("/all")
    public ResponseDTO<List<OrderDTO>> getAllOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.getAllOrder(orderDTO);
    }

}
