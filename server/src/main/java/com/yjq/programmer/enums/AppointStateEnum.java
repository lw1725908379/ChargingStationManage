package com.yjq.programmer.enums;

/**
 * @author wenLiu
 * @create 2023-12-30 17:15
 */
public enum AppointStateEnum {

    WAIT(1,"待使用"),

    USE(2,"充电中"),

    OVER(3,"已完成"),

    CANCEL(4,"已取消"),

    ;

    Integer code;

    String desc;

    AppointStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
