package com.yjq.programmer.enums;

/**
 * @author wenLiu
 * @create 2023-12-31 9:46
 */
public enum RepairStateEnum {

    NO(1,"未受理"),

    YES(2,"已受理"),

    CANCEL(3,"已撤销"),

    FINISH(4,"已完成"),

    ;

    Integer code;

    String desc;

    RepairStateEnum(Integer code, String desc) {
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
