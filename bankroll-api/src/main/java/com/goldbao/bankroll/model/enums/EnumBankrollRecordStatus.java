package com.goldbao.bankroll.model.enums;

/**
 * 配资记录状态
 * @author shuyu.fang
 */
public enum EnumBankrollRecordStatus {

    /**
     * 未分配homs （0）
     */
    NOT_ALLOCATION_HOMS,
    /**
     * 分配homs （1）
     */
    ALLOCATIONED_HOMS,

    /**
     * 完成 (2)
     * 将配资置为结束状态，但是不涉及还钱
     */
    FINISH,

    /**
     * 还款（3）
     * 配资已还款，配资完结
     */
    REPAYMENT
    ;

    public static EnumBankrollRecordStatus parse(int flag) {
        switch (flag) {
            case 0:
                return NOT_ALLOCATION_HOMS;
            case 1:
                return ALLOCATIONED_HOMS;
            case 2:
                return FINISH;
            case 3:
                return REPAYMENT;
            default:
                return null;
        }
    }
}
