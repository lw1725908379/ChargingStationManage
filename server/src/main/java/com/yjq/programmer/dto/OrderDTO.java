package com.yjq.programmer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wenLiu
 * @create 2023-12-30 22:10
 */
@ApiModel(description = "订单传输对象")
public class OrderDTO {
    @ApiModelProperty("订单ID")
    private String id;
    @ApiModelProperty("订单总价")
    private BigDecimal totalPrice;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("充电桩ID")
    private String chargeId;
    @ApiModelProperty("充电桩名称")
    private String chargeName;
    @ApiModelProperty("订单创建时间")
    private Date createTime;
    @ApiModelProperty("订单状态")
    private Integer state;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户信息")
    private UserDTO userDTO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", userId=").append(userId);
        sb.append(", chargeId=").append(chargeId);
        sb.append(", chargeName=").append(chargeName);
        sb.append(", createTime=").append(createTime);
        sb.append(", state=").append(state);
        sb.append(", username=").append(username);
        sb.append(", userDTO=").append(userDTO);
        sb.append("]");
        return sb.toString();
    }
}
