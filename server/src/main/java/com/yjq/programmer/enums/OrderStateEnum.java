package com.yjq.programmer.enums;

/**
 * @author wenLiu
 * @create 2023-12-30 22:16
 */
public enum OrderStateEnum {

    NO_PAY(1,"未支付"),

    PAYED(2,"已支付"),

    ;

    Integer code;

    String desc;

    OrderStateEnum(Integer code, String desc) {
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
