package com.yjq.programmer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yjq.programmer.annotation.ValidateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wenLiu
 * @create 2023-12-30 17:03
 */
@ApiModel(description = "预约充电桩时传递的数据模型")
public class AppointDTO {
    @ApiModelProperty("预约表唯一标识")
    private String id;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("充电桩ID")
    private String chargeId;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("预约日期")
    @ValidateEntity(required=true,errorRequiredMsg="预约日期不能为空！")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date day;
    @ApiModelProperty("预约时间段")
    @ValidateEntity(required=true,errorRequiredMsg="预约时间段不能为空！")
    private String time;
    @ApiModelProperty("预约状态")
    private Integer state;
    @ApiModelProperty("用户信息")
    private UserDTO userDTO;
    @ApiModelProperty("充电桩信息")
    private ChargeDTO chargeDTO;
    @ApiModelProperty("充电桩名称")
    private String chargeName;
    @ApiModelProperty("充电价格")
    private BigDecimal chargePrice;
    @ApiModelProperty("开始充电时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @ApiModelProperty("结束充电时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public ChargeDTO getChargeDTO() {
        return chargeDTO;
    }

    public void setChargeDTO(ChargeDTO chargeDTO) {
        this.chargeDTO = chargeDTO;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public BigDecimal getChargePrice() {
        return chargePrice;
    }

    public void setChargePrice(BigDecimal chargePrice) {
        this.chargePrice = chargePrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", chargeId=").append(chargeId);
        sb.append(", createTime=").append(createTime);
        sb.append(", day=").append(day);
        sb.append(", time=").append(time);
        sb.append(", state=").append(state);
        sb.append(", username=").append(username);
        sb.append(", userDTO=").append(userDTO);
        sb.append(", chargeDTO=").append(chargeDTO);
        sb.append(", chargeName=").append(chargeName);
        sb.append(", chargePrice=").append(chargePrice);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append("]");
        return sb.toString();
    }
}
