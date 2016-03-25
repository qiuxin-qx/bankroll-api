package com.goldbao.bankroll.model.enums;

/**
 * 用户账户状态枚举
 */
public enum EnumUserStatus {
    /**
     * 未激活
     */
    NOT_ACTIVATED(0),
    /**
     * 正常
     */
    NORMAL(1),
    /**
     * 冻结
     */
    FREEZE(2);

    private int status;

    EnumUserStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
