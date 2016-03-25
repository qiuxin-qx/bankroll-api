package com.goldbao.bankroll.model.enums;

/**
 * 交易状态枚举
 */
public enum EnumTradeStatus {

    /**
     * 提交(0)
     */
    INIT,

    /**
     * 成功(1)
     */
    SUCCESS,

    /**
     * 失败(2)
     */
    FAIL,

    /**
     * 待结算(3)
     */
    WAIT_SETTLEMENT,

    /**
     * 已结算(4)
     */
    SETTLEMENT,

    ;

    public static EnumTradeStatus parse(int status) {

        switch (status) {
            case 0:
                return INIT;
            case 1:
                return SUCCESS;
            case 2:
                return FAIL;
            case 3:
                return WAIT_SETTLEMENT;
            case 4:
                return SETTLEMENT;
            default:
                return null;
        }
    }
}
