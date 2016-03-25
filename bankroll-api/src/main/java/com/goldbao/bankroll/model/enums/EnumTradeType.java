package com.goldbao.bankroll.model.enums;

/**
 * 交易类型
 */
public enum EnumTradeType {

    /**
     * 充值（支付） - （0）
     */
    RECHARGE,
    /**
     * 资金冻结（配资） - （1）
     */
    FREEZE,

    /**
     * 配资
     */
    BANKROLL,

    /**
     * 服务费支出
     */
    MANAGEMENT_FEE,

    /**
     * 取现
     */
    CASH,

    ;



    public static EnumTradeType parse(int tradeType) {
        switch (tradeType) {
            case 0:
                return EnumTradeType.RECHARGE;
            case 1:
                return EnumTradeType.FREEZE;
            case 2:
                return EnumTradeType.BANKROLL;
            case 3:
                return EnumTradeType.MANAGEMENT_FEE;
            default:
                return null;
        }
    }

}
