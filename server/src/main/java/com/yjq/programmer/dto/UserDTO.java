package com.yjq.programmer.dto;

import com.yjq.programmer.annotation.ValidateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wenLiu
 * @create 2023-12-29 13:37
 */
@ApiModel(description = "用户传输对象")
public class UserDTO {
    @ApiModelProperty("用户唯一标识")
    private String id;

    @ApiModelProperty("用户昵称")
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=8,minLength=1,errorRequiredMsg="用户昵称不能为空！",errorMaxLengthMsg="用户昵称长度不能大于8！",errorMinLengthMsg="用户昵称不能为空！")
    private String username;

    @ApiModelProperty("用户密码")
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=16,minLength=6,errorRequiredMsg="用户密码不能为空！",errorMaxLengthMsg="用户密码长度不能大于16！",errorMinLengthMsg="用户密码长度不能小于6！")
    private String password;

    @ApiModelProperty("手机号码")
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=11,minLength=11,errorRequiredMsg="手机号码不能为空！",errorMaxLengthMsg="请输入11位手机号码！",errorMinLengthMsg="请输入11位手机号码！")
    private String phone;
    @ApiModelProperty("用户头像")
    private String headPic;
    @ApiModelProperty("用户性别")
    private Integer sex;
    @ApiModelProperty("用户评分")
    private Integer rate;
    @ApiModelProperty("用户角色ID")
    private Integer roleId;
    @ApiModelProperty("用户令牌")
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", headPic=").append(headPic);
        sb.append(", rate=").append(rate);
        sb.append(", sex=").append(sex);
        sb.append(", roleId=").append(roleId);
        sb.append(", token=").append(token);
        sb.append("]");
        return sb.toString();
    }
}
