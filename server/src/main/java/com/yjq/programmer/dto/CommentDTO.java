package com.yjq.programmer.dto;

import com.yjq.programmer.annotation.ValidateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author wenLiu
 * @create 2023-12-31 11:05
 */
@ApiModel(description = "留言传输对象")
public class CommentDTO {
    @ApiModelProperty("留言ID")
    private String id;
    @ApiModelProperty(value = "留言内容", required = true)
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=512,minLength=1,errorRequiredMsg="留言内容不能为空！",errorMaxLengthMsg="留言内容长度不能大于512！",errorMinLengthMsg="留言内容不能为空！")
    private String content;
    @ApiModelProperty("留言创建时间")
    private Date createTime;
    @ApiModelProperty("管理员回复内容")
    @ValidateEntity(requiredMaxLength=true,maxLength=512,errorMaxLengthMsg="留言回复内容长度不能大于512！")
    private String replyContent;
    @ApiModelProperty("用户ID")
    private String userId;
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

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", replyContent=").append(replyContent);
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", userDTO=").append(userDTO);
        sb.append("]");
        return sb.toString();
    }
}
