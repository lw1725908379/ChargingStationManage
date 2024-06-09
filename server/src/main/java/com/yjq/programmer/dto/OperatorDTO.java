package com.yjq.programmer.dto;

import com.yjq.programmer.annotation.ValidateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wenLiu
 * @create 2023-12-30 9:25
 */
@ApiModel(description = "运营商传输对象")
public class OperatorDTO {
    @ApiModelProperty("运营商ID")
    private String id;
    @ApiModelProperty(value = "运营商名称", required = true)
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=64,minLength=1,errorRequiredMsg="运营商名称不能为空！",errorMaxLengthMsg="运营商名称长度不能大于64！",errorMinLengthMsg="运营商名称不能为空！")
    private String name;
    @ApiModelProperty("运营商描述")
    @ValidateEntity(requiredMaxLength=true,maxLength=512,errorMaxLengthMsg="运营商描述长度不能大于512！")
    private String description;
    @ApiModelProperty("电站数量")
    private Integer stationNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStationNum() {
        return stationNum;
    }

    public void setStationNum(Integer stationNum) {
        this.stationNum = stationNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", stationNum=").append(stationNum);
        sb.append("]");
        return sb.toString();
    }
}
