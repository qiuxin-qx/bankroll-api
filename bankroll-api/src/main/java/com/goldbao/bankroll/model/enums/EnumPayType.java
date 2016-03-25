package com.goldbao.bankroll.model.enums;

/**
 * 支付类型
 * @author shuyu.fang
 */
public enum EnumPayType {

    /**
     * 中金
     */
    CFCA,

    /**
     * 线下转账
     */
    OFFLINE,
    ;

    public static Integer valueOf(EnumPayType payType) {
        if (payType != null)
            return payType.ordinal();
        return null;

    }
}
