package com.goldbao.bankroll.exception;

public enum EnumServiceMessage {
    USER_NOT_EXIST("401", "用户不存在"),
    USER_ERROR_PASSWORD("402", "用户密码错误"), 
    USER_LOGIN_PARAMETER_CANT_NULL("403", "用户名，密码不能为空"),
    USER_TOKEN_NOT_NULL("404", "token不能为空"),
    USER_REGISTER_USERNAME_EXIST("405", "用户名已存在"),
    USER_REGISTER_USER_PHONE_EXIST("410", "用户手机号码已存在"),
    USER_VALIDATE_CODE_NOT_EXIST("406", "尚未获取验证码"),
    USER_VALIDATE_ERROR("407", "验证码错误"),
    USER_REGISTER_PASSWORD_NOT_MATCH("408", "密码不匹配"),
    USER_TOKEN_NOT_EXIST("409", "无效的TOKEN"),

    ORG_ORGANIZE_NOT_EXIST("501", "组织机构不存在"),

    TRADE_RECORD_NOT_EXIST("601", "交易订单不存在"),
    TRADE_RECORD_DEALED("602", "交易订单已处理"),

    COMPANY_FUND_NOT_EXIST("701", "公司账户不存在"),

    SYS_USER_NOT_EXIST("801", "系统用户不存在"),
    SYS_USER_ERROR_PASSWORD("802", "系统用户登录密码错误"),
    SYS_USER_USERNAME_EXIST("803", "用户名已存在"),
    SYS_USER_TOKEN_NOT_EXIST("804", "无效的TOKEN"),

    BANKROLL_APPLY_NOT_EXIST("901", "配资申请记录不存在"),
    BANKROLL_APPLY_NOT_VERIFYING("902", "不是审核中的订单不能接受审核"),
    BANKROLL_APPLY_USER_BALANCE_NOT_ENOUGH("903", "账户余额不足"),
    BANKROLL_APPLY_USER_FREEZE_NOT_ENOUGH("904", "账户冻结资金不足"),
    BANKROLL_RECORD_NOT_EXIST("905", "配资记录不存在"),
    BANKROLL_RECORD_ALLOCATIONED_HOMS("906", "该配资记录已经分配homs帐号"),;
    
    private String code;
    private String message;

    EnumServiceMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
