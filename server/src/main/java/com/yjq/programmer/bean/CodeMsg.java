package com.yjq.programmer.bean;

/**
 * @author wenLiu
 * @create 2020-09-19 23:14
 */

/**
 * 错误码统一处理类，所有的错误码统一定义在这里
 */
public class CodeMsg {

    private Integer code;//错误码

    private String msg;//错误信息

    /**
     * 构造函数私有化即单例模式
     * 该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。
     * @param code
     * @param msg
     */
    private CodeMsg(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg() {

    }

    public Integer getCode() {
        return code;
    }



    public void setCode(Integer code) {
        this.code = code;
    }



    public String getMsg() {
        return msg;
    }



    public void setMsg(String msg) {
        this.msg = msg;
    }

    //通用错误码定义
    //处理成功消息码
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    //通用数据错误码
    public static CodeMsg DATA_ERROR = new CodeMsg(-1, "非法数据！");
    public static CodeMsg VALIDATE_ENTITY_ERROR = new CodeMsg(-2, "");
    public static CodeMsg CAPTCHA_EMPTY = new CodeMsg(-3, "验证码不能为空!");
    public static CodeMsg NO_PERMISSION = new CodeMsg(-4, "您没有当前操作的权限哦！");
    public static CodeMsg CAPTCHA_ERROR = new CodeMsg(-5, "验证码错误！");
    public static CodeMsg USER_SESSION_EXPIRED = new CodeMsg(-6, "还未登录或会话失效，请重新登录！");
    public static CodeMsg UPLOAD_PHOTO_SUFFIX_ERROR = new CodeMsg(-7, "图片格式不正确！");
    public static CodeMsg PHOTO_SURPASS_MAX_SIZE = new CodeMsg(-8, "上传的图片不能超过2MB！");
    public static CodeMsg PHOTO_FORMAT_NOT_CORRECT = new CodeMsg(-9, "上传的图片格式不正确！");

    public static CodeMsg SAVE_FILE_EXCEPTION = new CodeMsg(-10, "保存文件异常！");
    public static CodeMsg FILE_EXPORT_ERROR = new CodeMsg(-11, "文件导出失败！");
    public static CodeMsg SYSTEM_ERROR = new CodeMsg(-12, "系统出现了错误，请联系管理员！");
    public static CodeMsg NO_AUTHORITY = new CodeMsg(-13, "不好意思，您没有权限操作哦！");
    public static CodeMsg CAPTCHA_EXPIRED = new CodeMsg(-14, "验证码已过期，请刷新验证码！");
    public static CodeMsg COMMON_ERROR = new CodeMsg(-15, "");
    public static CodeMsg PHOTO_EMPTY = new CodeMsg(-16, "上传的图片不能为空！");
    public static CodeMsg PHOTO_ILLEGAL_IMAGE_UPLOAD = new CodeMsg(-17, "禁止上传违规图片！");


    //用户管理错误码
    public static CodeMsg USER_ADD_ERROR = new CodeMsg(-1000, "用户信息添加失败，请联系管理员！");
    public static CodeMsg USER_NOT_EXIST  = new CodeMsg(-1001, "该用户不存在！");
    public static CodeMsg USER_EDIT_ERROR = new CodeMsg(-1002, "用户信息编辑失败，请联系管理员！");
    public static CodeMsg USER_DELETE_ERROR = new CodeMsg(-1003, "用户信息删除失败，请联系管理员！");
    public static CodeMsg USER_PHONE_EXIST = new CodeMsg(-1004, "用户手机号码已经注册，请换一个！");
    public static CodeMsg USERNAME_EMPTY = new CodeMsg(-1005, "用户昵称不能为空！");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(-1006, "用户密码不能为空！");
    public static CodeMsg USERNAME_PASSWORD_ERROR = new CodeMsg(-1007, "用户昵称或密码错误！");
    public static CodeMsg REPASSWORD_EMPTY = new CodeMsg(-1008, "确认密码不能为空！");
    public static CodeMsg REPASSWORD_ERROR = new CodeMsg(-1009, "确认密码不一致！");
    public static CodeMsg USER_REGISTER_ERROR = new CodeMsg(-1010, "注册用户失败，请联系管理员！");
    public static CodeMsg USER_NOT_IS_ADMIN = new CodeMsg(-1011, "只有管理员角色才能登录后台系统！");
    public static CodeMsg USER_NAME_EXIST = new CodeMsg(-1012, "用户昵称重复，请换一个！");

    // 运营商管理错误码
    public static CodeMsg OPERATOR_ADD_ERROR = new CodeMsg(-2000, "运营商信息添加失败，请联系管理员！");
    public static CodeMsg OPERATOR_NOT_EXIST  = new CodeMsg(-2001, "该运营商不存在！");
    public static CodeMsg OPERATOR_EDIT_ERROR = new CodeMsg(-2002, "运营商信息编辑失败，请联系管理员！");
    public static CodeMsg OPERATOR_DELETE_ERROR = new CodeMsg(-2003, "运营商信息删除失败，请联系管理员！");
    public static CodeMsg OPERATOR_NAME_EXIST = new CodeMsg(-2004, "运营商名称重复，请换一个！");

    // 电站管理错误码
    public static CodeMsg STATION_ADD_ERROR = new CodeMsg(-3000, "电站信息添加失败，请联系管理员！");
    public static CodeMsg STATION_NOT_EXIST  = new CodeMsg(-3001, "该电站不存在！");
    public static CodeMsg STATION_EDIT_ERROR = new CodeMsg(-3002, "电站信息编辑失败，请联系管理员！");
    public static CodeMsg STATION_DELETE_ERROR = new CodeMsg(-3003, "电站信息删除失败，请联系管理员！");

    // 充电桩管理错误码
    public static CodeMsg CHARGE_ADD_ERROR = new CodeMsg(-4000, "充电桩信息添加失败，请联系管理员！");
    public static CodeMsg CHARGE_NOT_EXIST  = new CodeMsg(-4001, "该充电桩不存在！");
    public static CodeMsg CHARGE_EDIT_ERROR = new CodeMsg(-4002, "充电桩信息编辑失败，请联系管理员！");
    public static CodeMsg CHARGE_DELETE_ERROR = new CodeMsg(-4003, "充电桩信息删除失败，请联系管理员！");

    // 预约管理错误码
    public static CodeMsg APPOINT_ADD_ERROR = new CodeMsg(-5000, "预约失败，请联系管理员！");
    public static CodeMsg APPOINT_DAY_EXIST = new CodeMsg(-5001, "此预约日期时间段已被人预约，请换一个！");
    public static CodeMsg APPOINT_EDIT_ERROR = new CodeMsg(-5002, "预约信息编辑失败，请联系管理员！");
    public static CodeMsg CHARGE_REPAIR_ERROR = new CodeMsg(-5003, "此充电桩正在维修，不能预约！");
    public static CodeMsg APPOINT_DELETE_ERROR = new CodeMsg(-5004, "预约信息删除失败，请联系管理员！");
    public static CodeMsg RATE_NOT_ENOUGH = new CodeMsg(-5005, "信誉积分小于80分无法预约，请联系管理员！");

    // 订单管理错误码
    public static CodeMsg ORDER_ADD_ERROR = new CodeMsg(-6000, "订单创建失败，请联系管理员！");
    public static CodeMsg ORDER_EDIT_ERROR = new CodeMsg(-6001, "订单信息编辑失败，请联系管理员！");
    public static CodeMsg ORDER_DELETE_ERROR = new CodeMsg(-6002, "订单信息删除失败，请联系管理员！");

    // 报修管理错误码
    public static CodeMsg REPAIR_ADD_ERROR = new CodeMsg(-7000, "报修信息创建失败，请联系管理员！");
    public static CodeMsg REPAIR_EDIT_ERROR = new CodeMsg(-7001, "报修信息编辑失败，请联系管理员！");
    public static CodeMsg REPAIR_DELETE_ERROR = new CodeMsg(-7002, "报修信息删除失败，请联系管理员！");
    public static CodeMsg CHARGE_ALREADY_REPAIR = new CodeMsg(-7003, "此充电桩已经在报修中，请勿重复操作！");
    public static CodeMsg REPAIR_ALREADY_EXIST = new CodeMsg(-7004, "此充电桩已有人报修，请勿重复操作！");

    // 留言管理错误码
    public static CodeMsg COMMENT_ADD_ERROR = new CodeMsg(-8000, "留言信息创建失败，请联系管理员！");
    public static CodeMsg COMMENT_EDIT_ERROR = new CodeMsg(-8001, "留言信息编辑失败，请联系管理员！");
    public static CodeMsg COMMENT_DELETE_ERROR = new CodeMsg(-8002, "留言信息删除失败，请联系管理员！");

    // 告警管理错误码
    public static CodeMsg WARN_ADD_ERROR = new CodeMsg(-9000, "告警信息创建失败，请联系管理员！");
    public static CodeMsg WARN_EDIT_ERROR = new CodeMsg(-9001, "告警信息编辑失败，请联系管理员！");
    public static CodeMsg WARN_DELETE_ERROR = new CodeMsg(-9002, "告警信息删除失败，请联系管理员！");

    // 信誉积分管理错误码
    public static CodeMsg RATE_ADD_ERROR = new CodeMsg(-10000, "信誉积分记录添加失败，请联系管理员！");
    public static CodeMsg RATE_EDIT_ERROR = new CodeMsg(-10001, "信誉积分记录编辑失败，请联系管理员！");
    public static CodeMsg RATE_DELETE_ERROR = new CodeMsg(-10002, "信誉积分记录删除失败，请联系管理员！");

    // 微服务
    public static CodeMsg  FEIGN_FAIL_LOAD_DATA = new CodeMsg(-100000, "获取数据失败！");

}
