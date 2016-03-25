package com.goldbao.pay;

import java.math.BigDecimal;

/**
 * 代扣参数
 * @author shuyu.fang
 */
public class WithHoldingParameter {

    private String orderNo;

    private String serialNo;

    private BigDecimal amount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
