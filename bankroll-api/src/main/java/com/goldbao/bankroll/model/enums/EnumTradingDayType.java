package com.goldbao.bankroll.model.enums;

/**
 * @author shuyu.fang
 */
public enum EnumTradingDayType {

    /**
     * 交易日
     */
    OPEN,
    /**
     * 休市
     */
    CLOSE;

    public static EnumTradingDayType parse(int daytype) {
        switch (daytype) {
            case 0:
                return EnumTradingDayType.OPEN;
            case 1:
                return EnumTradingDayType.CLOSE;
            default:
                return null;
        }
    }
}
