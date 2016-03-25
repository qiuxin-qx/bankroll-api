package com.goldbao.bankroll.model.enums;

/**
 * 验证码使用目的
 */
public enum EnumValidateCodePurpose {

    /**
     * 注册
     */
    REGISTER(0);

    private Integer flag;

    EnumValidateCodePurpose(Integer flag) {
        this.flag = flag;
    }

    public Integer getFlag() {
        return flag;
    }
}
