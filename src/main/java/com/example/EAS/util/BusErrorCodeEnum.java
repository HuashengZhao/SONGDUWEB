package com.example.EAS.util;


public enum BusErrorCodeEnum {

    DEFAULT_CODE(500, "default", "默认错误!"),
    NONE_PARAM(40000, "default", "数据不存在!"),
    NO_PARAM(40007, "default", "缺少参数!"),
    USER_LOCKED(40005, "login", "用户被冻结!"),
    PASSWORD_ERROR(40006, "login", "账户密码错误!"),
    USERNAME_EXIST(40101, "sysUser", "用户名已存在!"),
    USER_NOT_EXIST(40102, "sysUser", "用户不存在!"),
    USER_PASSWORD_ERROR(40103, "sysUser", "账户密码错误!");


    private int code;

    private String message;

    private String busErrorType;

    BusErrorCodeEnum(int code, String busErrorType, String message) {
        this.code = code;
        this.message = message;
        this.busErrorType = busErrorType;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public String busErrorType() {
        return busErrorType;
    }
}
