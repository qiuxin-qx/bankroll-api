package com.goldbao.bankroll.model.enums;

/**
 *
 * 公司账户类型
 * @author shuyu.fang
 */
public enum EnumCompanyFundType {

    /**
     * 终端用户充值累计，或者叫平台用户可提现资金总额更合适
     */
    RECHARGE,
    /**
     * 管理费（月息）收入
     */
    MANAGEMENT_FEE,
    /**
     * 配资累计
     */
    BANKROLL;
}
