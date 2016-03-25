package com.goldbao.bankroll.model.enums;

/**
 * 账单状态
 * @author shuyu.fang
 */
public enum EnumBankrollBillStatus {

    /**
     * 未还款
     */
    NOT_REPAYMENT,
    /**
     * 已还款
     */
    REPAYMENTED,

    /**
     * 过期未还
     */
    OVERDUE_NOT_REPAYMENT,

    /**
     * 系统后台取消，可用于提前终止协议
     */
    SYSTEM_CANCEL,
    /**
     * 用户取消，可用于提前终止协议
     */
    USER_CANCEL,
    ;

    public static EnumBankrollBillStatus parse(int flag) {
        switch (flag) {
            case 0:
                return EnumBankrollBillStatus.NOT_REPAYMENT;
            case 1:
                return EnumBankrollBillStatus.REPAYMENTED;
            case 2:
                return EnumBankrollBillStatus.OVERDUE_NOT_REPAYMENT;
            case 3:
                return EnumBankrollBillStatus.SYSTEM_CANCEL;
            case 4:
                return EnumBankrollBillStatus.USER_CANCEL;
            default:
                return null;
        }
    }
}
