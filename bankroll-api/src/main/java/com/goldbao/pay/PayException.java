package com.goldbao.pay;

/**
 * 支付的异常处理类
 */
public class PayException extends Exception {
	private static final long serialVersionUID = -9175656875383894459L;
	private String code;

    public PayException(String message) {
        super(message);
        code = "500";
    }

    public PayException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
