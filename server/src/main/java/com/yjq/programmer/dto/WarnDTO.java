package com.yjq.programmer.dto;

import com.yjq.programmer.annotation.ValidateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author wenLiu
 * @create 2023-12-31 12:12
 */
@ApiModel(description = "告警传输对象")
public class WarnDTO {
    @ApiModelProperty("告警唯一标识")
    private String id;
    @ApiModelProperty("用户唯一标识")
    private String userId;
    @ApiModelProperty("告警内容描述")
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=512,minLength=1,errorRequiredMsg="告警内容描述不能为空！",errorMaxLengthMsg="告警内容描述长度不能大于512！",errorMinLengthMsg="告警内容描述不能为空！")
    private String content;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户评分")
    private Integer rate;
    @ApiModelProperty("用户传输对象")
    private UserDTO userDTO;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", username=").append(username);
        sb.append(", userDTO=").append(userDTO);
        sb.append(", rate=").append(rate);
        sb.append("]");
        return sb.toString();
    }
}
