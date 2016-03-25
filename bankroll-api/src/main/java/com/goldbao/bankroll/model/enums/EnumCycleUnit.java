package com.goldbao.bankroll.model.enums;

/**
 * 配资周期单位
 */
public enum EnumCycleUnit {

    /**
     * 按天配资
     */
    DAY(0),

    /**
     * 按月配资
     */
    MONTH(1);

    private Integer unit;

    EnumCycleUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getUnit() {
        return unit;
    }

    public static EnumCycleUnit parse(int unit) {
        switch (unit) {
            case 0:
                return EnumCycleUnit.DAY;
            case 1:
                return EnumCycleUnit.MONTH;
            default:
                return null;
        }
    }
}
