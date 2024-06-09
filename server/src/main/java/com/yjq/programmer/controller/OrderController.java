package com.yjq.programmer.controller;

import com.yjq.programmer.dto.OrderDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "订单管理")
public class OrderController {

    @Resource
    private IOrderService orderService;

    /**
     * 分页获取订单数据
     * @param pageDTO
     * @return
     */
    @ApiOperation("分页获取订单数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -6000, message = "订单创建失败，请联系管理员！"),
            @ApiResponse(code = -6001, message = "订单信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -6002, message = "订单信息删除失败，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/list")
    public ResponseDTO<PageDTO<OrderDTO>> getOrderList(@RequestBody PageDTO<OrderDTO> pageDTO){
        return orderService.getOrderList(pageDTO);
    }

    /**
     * 更改订单状态信息
     * @param orderDTO
     * @return
     */
    @ApiOperation("更改订单状态信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -6000, message = "订单创建失败，请联系管理员！"),
            @ApiResponse(code = -6001, message = "订单信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -6002, message = "订单信息删除失败，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/edit_state")
    public ResponseDTO<Boolean> editOrderState(@RequestBody OrderDTO orderDTO) {
        return orderService.editOrderState(orderDTO);
    }

    /**
     * 删除订单信息
     * @param orderDTO
     * @return
     */
    @ApiOperation("删除订单信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -6000, message = "订单创建失败，请联系管理员！"),
            @ApiResponse(code = -6001, message = "订单信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -6002, message = "订单信息删除失败，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/delete")
    public ResponseDTO<Boolean> deleteOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.deleteOrder(orderDTO);
    }

    /**
     * 获取全部订单数据
     * @param orderDTO
     * @return
     */
    @ApiOperation("获取全部订单数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "保存成功！", response = ResponseDTO.class),
            @ApiResponse(code = -6000, message = "订单创建失败，请联系管理员！"),
            @ApiResponse(code = -6001, message = "订单信息编辑失败，请联系管理员！"),
            @ApiResponse(code = -6002, message = "订单信息删除失败，请联系管理员！"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PostMapping("/all")
    public ResponseDTO<List<OrderDTO>> getAllOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.getAllOrder(orderDTO);
    }

}
