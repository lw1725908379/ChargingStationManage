package com.yjq.programmer.dto;

import com.yjq.programmer.annotation.ValidateEntity;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2023-12-30 10:38
 */
public class StationDTO {

    private String id;

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=64,minLength=1,errorRequiredMsg="电站名称不能为空！",errorMaxLengthMsg="电站名称长度不能大于64！",errorMinLengthMsg="电站名称不能为空！")
    private String name;

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=256,minLength=1,errorRequiredMsg="电站位置不能为空！",errorMaxLengthMsg="电站位置长度不能大于256！",errorMinLengthMsg="电站位置不能为空！")
    private String location;

    private String photo;

    private Integer chargeNum;

    private Integer availableChargeNum;

    @ValidateEntity(required=true,errorRequiredMsg="电站所属运营商不能为空！")
    private String operatorId;

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
