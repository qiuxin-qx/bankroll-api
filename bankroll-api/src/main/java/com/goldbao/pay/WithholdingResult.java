package com.goldbao.pay;

import java.math.BigDecimal;

/**
 * 代扣返回结果
 * @author shuyu.fang
 */
public class WithholdingResult {

    private String orderNo;

    private String serialNo;

    private BigDecimal amount;

    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
