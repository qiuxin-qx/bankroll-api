package com.goldbao.bankroll.model.enums;

/**
 * 配资申请状态
 * @author shuyu.fang
 */
public enum EnumBankrollApplyStatus {

    /**
     * 审核中（0）
     */
    VERIFYING,
    /**
     * 通过（1）
     */
    PASS,

    /**
     * 拒绝（2）
     */
    REFUSE,

    /**
     * 自己取消（3）
     */
    CANCEL_SELF,

    /**
     * 系统取消（4）
     */
    CANCEL_SYSTEM,

    /**
     * 超时（5）
     */
    EXPIRED;

    public static EnumBankrollApplyStatus parse(int status) {
        switch (status) {
            case 0:
                return EnumBankrollApplyStatus.VERIFYING;
            case 1:
                return EnumBankrollApplyStatus.PASS;
            case 2:
                return EnumBankrollApplyStatus.REFUSE;
            case 3:
                return EnumBankrollApplyStatus.CANCEL_SELF;
            case 4:
                return EnumBankrollApplyStatus.CANCEL_SYSTEM;
            case 5:
                return EnumBankrollApplyStatus.CANCEL_SYSTEM;
            case 6:
                return EnumBankrollApplyStatus.EXPIRED;
            default:
                return null;
        }
    }

}
