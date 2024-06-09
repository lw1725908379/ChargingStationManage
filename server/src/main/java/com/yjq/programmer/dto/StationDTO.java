package com.yjq.programmer.dto;

import com.yjq.programmer.annotation.ValidateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wenLiu
 * @create 2023-12-30 10:38
 */
@ApiModel(description = "电站传输对象")
public class StationDTO {
    @ApiModelProperty("电站唯一标识")
    private String id;
    @ApiModelProperty("电站名称")
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=64,minLength=1,errorRequiredMsg="电站名称不能为空！",errorMaxLengthMsg="电站名称长度不能大于64！",errorMinLengthMsg="电站名称不能为空！")
    private String name;
    @ApiModelProperty("电站位置")
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=256,minLength=1,errorRequiredMsg="电站位置不能为空！",errorMaxLengthMsg="电站位置长度不能大于256！",errorMinLengthMsg="电站位置不能为空！")
    private String location;
    @ApiModelProperty("电站照片")
    private String photo;
    @ApiModelProperty("充电桩数量")
    private Integer chargeNum;
    @ApiModelProperty("可用充电桩数量")
    private Integer availableChargeNum;
    @ApiModelProperty("运营商唯一标识")
    @ValidateEntity(required=true,errorRequiredMsg="电站所属运营商不能为空！")
    private String operatorId;
    @ApiModelProperty("运营商信息")
    private OperatorDTO operatorDTO;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getChargeNum() {
        return chargeNum;
    }

    public void setChargeNum(Integer chargeNum) {
        this.chargeNum = chargeNum;
    }

    public Integer getAvailableChargeNum() {
        return availableChargeNum;
    }

    public void setAvailableChargeNum(Integer availableChargeNum) {
        this.availableChargeNum = availableChargeNum;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public OperatorDTO getOperatorDTO() {
        return operatorDTO;
    }

    public void setOperatorDTO(OperatorDTO operatorDTO) {
        this.operatorDTO = operatorDTO;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", location=").append(location);
        sb.append(", photo=").append(photo);
        sb.append(", chargeNum=").append(chargeNum);
        sb.append(", availableChargeNum=").append(availableChargeNum);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorDTO=").append(operatorDTO);
        sb.append("]");
        return sb.toString();
    }
}
