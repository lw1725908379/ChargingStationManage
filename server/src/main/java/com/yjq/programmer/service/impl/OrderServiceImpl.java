package com.yjq.programmer.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.*;
import com.yjq.programmer.domain.*;
import com.yjq.programmer.dto.OrderDTO;
import com.yjq.programmer.dto.PageDTO;
import com.yjq.programmer.dto.ResponseDTO;
import com.yjq.programmer.dto.UserDTO;
import com.yjq.programmer.enums.AppointStateEnum;
import com.yjq.programmer.enums.OrderStateEnum;
import com.yjq.programmer.enums.RateTypeEnum;
import com.yjq.programmer.service.IOrderService;
import com.yjq.programmer.utils.CommonUtil;
import com.yjq.programmer.utils.CopyUtil;
import com.yjq.programmer.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RateMapper rateMapper;

    @Resource
    private AppointMapper appointMapper;

    @Resource
    private ChargeMapper chargeMapper;

    /**
     * 分页获取订单数据
     * @param pageDTO
     * @return
     */
    @Override
    public ResponseDTO<PageDTO<OrderDTO>> getOrderList(PageDTO<OrderDTO> pageDTO) {
        // 更新下最新预约数据
        List<Appoint> appointList = appointMapper.selectByExample(new AppointExample());
        for(Appoint appoint : appointList) {
            // 当前时间超过结束时间，且状态为待使用的预约，状态自动改为已取消
            // 当前时间超过结束时间，且状态为充电中的预约，状态自动改为已完成
            String[] splitTime = appoint.getTime().split("-");
            Date endDate = CommonUtil.getFormatterDate(CommonUtil.getFormatterDate(appoint.getDay(), "yyyy-MM-dd") + " " + splitTime[1], "yyyy-MM-dd HH:mm");
            if(new Date().getTime() > endDate.getTime()) {
                if(AppointStateEnum.WAIT.getCode().equals(appoint.getState())) {
                    appoint.setState(AppointStateEnum.CANCEL.getCode());
                } else if(AppointStateEnum.USE.getCode().equals(appoint.getState())) {
                    appoint.setState(AppointStateEnum.OVER.getCode());
                    appoint.setEndTime(endDate);
                    Charge charge = chargeMapper.selectByPrimaryKey(appoint.getChargeId());
                    BigDecimal totalPrice = charge.getPrice().multiply(new BigDecimal(DateUtil.between(appoint.getStartTime(), endDate, DateUnit.MINUTE)));
                    Order order = new Order();
                    order.setId(UuidUtil.getShortUuid());
                    order.setCreateTime(new Date());
                    order.setUserId(appoint.getUserId());
                    order.setChargeId(appoint.getChargeId());
                    order.setChargeName(appoint.getChargeName());
                    order.setTotalPrice(totalPrice);
                    if(orderMapper.insertSelective(order) == 0) {
                        return ResponseDTO.errorByMsg(CodeMsg.ORDER_ADD_ERROR);
                    }
                }
                appointMapper.updateByPrimaryKeySelective(appoint);
            }
        }

        // 查询订单数据
        OrderExample orderExample = new OrderExample();
        // 不知道当前页多少，默认为第一页
        if(pageDTO.getPage() == null){
            pageDTO.setPage(1);
        }
        // 不知道每页多少条记录，默认为每页5条记录
        if(pageDTO.getSize() == null){
            pageDTO.setSize(5);
        }
        OrderExample.Criteria c1 = orderExample.createCriteria();
        if(pageDTO.getParam() != null) {
            OrderDTO orderDTO = pageDTO.getParam();
            if(!CommonUtil.isEmpty(orderDTO.getUserId())) {
               c1.andUserIdEqualTo(orderDTO.getUserId());
            } else {
                UserExample userExample = new UserExample();
                userExample.createCriteria().andUsernameLike("%" + orderDTO.getUsername() + "%");
                List<String> userIdList = userMapper.selectByExample(userExample).stream().map(User::getId).collect(Collectors.toList());
                if(userIdList.size() == 0) {
                    pageDTO.setTotal(0L);
                    pageDTO.setList(new ArrayList<>());
                    return ResponseDTO.success(pageDTO);
                }
                c1.andUserIdIn(userIdList);
            }
            c1.andIdLike("%" + orderDTO.getId() + "%");
            if(orderDTO.getState() != null && orderDTO.getState() != 0) {
                c1.andStateEqualTo(orderDTO.getState());
            }
        }
        orderExample.setOrderByClause("create_time desc");
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getSize());
        // 分页查出订单数据
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        // 获取数据的总数
        pageDTO.setTotal(pageInfo.getTotal());
        // 将domain类型数据  转成 DTO类型数据
        List<OrderDTO> orderDTOList = CopyUtil.copyList(orderList, OrderDTO.class);
        for(OrderDTO orderDTO : orderDTOList) {
            User user = userMapper.selectByPrimaryKey(orderDTO.getUserId());
            orderDTO.setUserDTO(CopyUtil.copy(user, UserDTO.class));
        }
        pageDTO.setList(orderDTOList);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * 修改订单状态
     * @param orderDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> editOrderState(OrderDTO orderDTO) {
        if(CommonUtil.isEmpty(orderDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        Order orderDB = orderMapper.selectByPrimaryKey(orderDTO.getId());
        Order order = CopyUtil.copy(orderDTO, Order.class);
        User user = userMapper.selectByPrimaryKey(orderDB.getUserId());
        if(user == null) {
            return ResponseDTO.errorByMsg(CodeMsg.USER_NOT_EXIST);
        }
        if(!OrderStateEnum.PAYED.getCode().equals(orderDB.getState()) && OrderStateEnum.PAYED.getCode().equals(order.getState())) {
            Rate rate = new Rate();
            rate.setId(UuidUtil.getShortUuid());
            if(user.getRate() + 5 >= 100) {
                user.setRate(100);
            } else {
                user.setRate(user.getRate() + 5);
            }
            rate.setNowScore(user.getRate());
            rate.setScore(5);
            rate.setType(RateTypeEnum.ADD.getCode());
            rate.setUserId(orderDB.getUserId());
            rate.setCreateTime(new Date());
            rate.setContent("成功支付，奖励信誉分5分！");
            if(rateMapper.insertSelective(rate) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.RATE_ADD_ERROR);
            }
            if(userMapper.updateByPrimaryKeySelective(user) == 0) {
                return ResponseDTO.errorByMsg(CodeMsg.USER_EDIT_ERROR);
            }
        }
        if(orderMapper.updateByPrimaryKeySelective(order) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.ORDER_EDIT_ERROR);
        }
        return ResponseDTO.success(true);
    }

    /**
     * 删除订单信息
     * @param orderDTO
     * @return
     */
    @Override
    public ResponseDTO<Boolean> deleteOrder(OrderDTO orderDTO) {
        if(CommonUtil.isEmpty(orderDTO.getId())) {
            return ResponseDTO.errorByMsg(CodeMsg.DATA_ERROR);
        }
        if(orderMapper.deleteByPrimaryKey(orderDTO.getId()) == 0) {
            return ResponseDTO.errorByMsg(CodeMsg.ORDER_DELETE_ERROR);
        }
        return ResponseDTO.successByMsg(true, "删除成功！");
    }

    /**
     * 获取全部订单数据
     * @param orderDTO
     * @return
     */
    @Override
    public ResponseDTO<List<OrderDTO>> getAllOrder(OrderDTO orderDTO) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if(!CommonUtil.isEmpty(orderDTO.getUserId())) {
            criteria.andUserIdEqualTo(orderDTO.getUserId());
        }
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        return ResponseDTO.success(CopyUtil.copyList(orderList, OrderDTO.class));
    }
}
