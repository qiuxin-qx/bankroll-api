package com.goldbao.bankroll.model.enums;

/**
 * 是否限制仓位
 */
public enum EnumIsLimitPosition {

    /**
     * 不限制
     */
    NO_LIMIT(0),
    /**
     * 限制但不限创业板
     */
    LIMIT(1),
    /**
     *
     */
    MORE_LIMIT(2);

    private Integer flag;

    EnumIsLimitPosition(Integer flag) {
        this.flag = flag;
    }

    public Integer getFlag() {
        return flag;
    }


    @Override
    public String toString() {
        switch (flag) {
            case 0:
                return "";
            case 1:
                return "仓位有限制";
            case 2:
                return "仓位有限制，创业板仓位有限制";
            default:
                return "";
        }
    }
}
