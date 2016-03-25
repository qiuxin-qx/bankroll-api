package com.goldbao.pay;

import java.math.BigDecimal;
import java.util.List;

/**
 * 结算参数
 * @author shuyu.fang
 */
public class SettlementParameter {

    private String orderNo;

    private String serialNumber;

    private BigDecimal amount;

    private List<String> repaymentList;

    private BankAccount2 bankAccount;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<String> getRepaymentList() {
        return repaymentList;
    }

    public void setRepaymentList(List<String> repaymentList) {
        this.repaymentList = repaymentList;
    }

    public BankAccount2 getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount2 bankAccount) {
        this.bankAccount = bankAccount;
    }
}
