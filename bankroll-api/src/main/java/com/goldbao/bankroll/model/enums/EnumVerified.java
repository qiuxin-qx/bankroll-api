package com.goldbao.bankroll.model.enums;

/**
 * 身份，邮箱，手机号码等的验证
 * @author shuyu.fang
 */
public enum EnumVerified {

    NOT_VERIFIED,

    VERIFIED,

    FAILED;

    public static EnumVerified parse(int flag) {
        switch (flag) {
            case 0:
                return EnumVerified.NOT_VERIFIED;
            case 1:
                return EnumVerified.VERIFIED;
            case 2:
                return EnumVerified.FAILED;
            default:
                return EnumVerified.NOT_VERIFIED;
        }
    }
}
