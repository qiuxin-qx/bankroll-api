package com.goldbao.bankroll.model.bankroll;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 配资申请统计
 * @author shuyu.fang
 */
public class BankrollApplyStatDTO {

    private BigInteger applyCount; // 总申请数

    private BigDecimal totalMoney; // 总配资金额

    private BigDecimal totalDeposit; // 总配资本金

    public BigInteger getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(BigInteger applyCount) {
        this.applyCount = applyCount;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(BigDecimal totalDeposit) {
        this.totalDeposit = totalDeposit;
    }
}
