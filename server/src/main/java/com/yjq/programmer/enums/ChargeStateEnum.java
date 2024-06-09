package com.yjq.programmer.enums;

/**
 * @author wenLiu
 * @create 2023-12-30 12:38
 */
public enum ChargeStateEnum {

    NORMAL(1,"正常"),

    REPAIR(2,"报修中"),

    ;

    Integer code;

    String desc;

    ChargeStateEnum(Integer code, String desc) {
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
