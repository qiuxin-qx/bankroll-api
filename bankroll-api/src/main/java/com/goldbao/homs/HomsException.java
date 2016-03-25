package com.goldbao.homs;

/**
 * homs 调用异常封装
 */
public class HomsException extends RuntimeException {

	private static final long serialVersionUID = -6100046275180658272L;
	private String code;

    public HomsException() {
        super();
    }

    public HomsException(String message) {
        super(message);
        this.code = "500";
    }

    public HomsException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
