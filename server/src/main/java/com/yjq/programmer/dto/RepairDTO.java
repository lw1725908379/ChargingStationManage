package com.yjq.programmer.dto;

import com.yjq.programmer.annotation.ValidateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author wenLiu
 * @create 2023-12-31 9:53
 */
@ApiModel(description = "报修传输对象")
public class RepairDTO {
    @ApiModelProperty("报修ID")
    private String id;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("充电桩ID")
    private String chargeId;
    @ApiModelProperty("报修状态：1-未受理，2-已受理，3-已撤销，4-已完成")
    private Integer state;
    @ApiModelProperty("报修时间")
    private Date createTime;
    @ApiModelProperty("充电桩名称")
    private String chargeName;
    @ApiModelProperty("用户昵称")
    private String username;
    @ApiModelProperty("报修内容描述")
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=512,minLength=1,errorRequiredMsg="报修内容描述不能为空！",errorMaxLengthMsg="报修内容描述长度不能大于512！",errorMinLengthMsg="报修内容描述不能为空！")
    private String description;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", chargeName=").append(chargeName);
        sb.append(", username=").append(username);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }
}
