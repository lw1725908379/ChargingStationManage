package com.yjq.programmer.dto;

import com.yjq.programmer.annotation.ValidateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author wenLiu
 * @create 2023-12-30 12:23
 */
@ApiModel(description = "充电桩传输对象")
public class ChargeDTO {
    @ApiModelProperty("充电桩ID")
    private String id;
    @ApiModelProperty(value = "充电桩名称", required = true)
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=32,minLength=1,errorRequiredMsg="充电桩名称不能为空！",errorMaxLengthMsg="充电桩名称长度不能大于32！",errorMinLengthMsg="充电桩名称不能为空！")
    private String name;
    @ApiModelProperty(value = "所属电站ID", required = true)
    @ValidateEntity(required=true,requiredMinLength=true,minLength=1,errorRequiredMsg="充电桩所属电站不能为空！",errorMinLengthMsg="充电桩所属电站不能为空！")
    private String stationId;
    @ApiModelProperty("充电桩照片")
    private String photo;
    @ApiModelProperty("充电桩状态")
    private Integer state;
    @ApiModelProperty(value = "充电价格", required = true)
    @ValidateEntity(required=true,requiredMaxValue=true,requiredMinValue=true,maxValue=99999999.99,minValue=0.00,errorRequiredMsg="充电桩收费价格不能小于0.00！",errorMaxValueMsg="充电桩收费价格不能大于99999999.99元/分钟！",errorMinValueMsg="充电桩收费价格不能小于0.00！")
    private BigDecimal price;
    @ApiModelProperty(value = "充电桩规格描述", required = true)
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=512,minLength=1,errorRequiredMsg="充电桩规格描述不能为空！",errorMaxLengthMsg="充电桩规格描述长度不能大于512！",errorMinLengthMsg="充电桩规格描述不能为空！")
    private String description;
    @ApiModelProperty("电站信息")
    private StationDTO stationDTO;

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

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StationDTO getStationDTO() {
        return stationDTO;
    }

    public void setStationDTO(StationDTO stationDTO) {
        this.stationDTO = stationDTO;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", stationId=").append(stationId);
        sb.append(", photo=").append(photo);
        sb.append(", state=").append(state);
        sb.append(", price=").append(price);
        sb.append(", description=").append(description);
        sb.append(", stationDTO=").append(stationDTO);
        sb.append("]");
        return sb.toString();
    }
}
