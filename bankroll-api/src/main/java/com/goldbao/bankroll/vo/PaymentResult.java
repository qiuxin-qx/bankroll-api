package com.goldbao.bankroll.vo;

import java.util.Map;

/**
 * 支付订单
 * @author shuyu.fang
 */
public class PaymentResult {

    private String orderNo;

    private Map<String, String> form;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Map<String, String> getForm() {
        return form;
    }

    public void setForm(Map<String, String> form) {
        this.form = form;
    }
}
