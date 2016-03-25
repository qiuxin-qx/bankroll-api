package com.goldbao.bankroll.vo;

import com.goldbao.bankroll.exception.ServiceException;

/**
 * 接口对外统一模板
 * <pre>
 * {
 *     status   : "000",
 *     message  : "成功",
 *     data     :
 *     {
 *
 *     }
 * }
 * </pre>
 */
public class ModelTemplate<T> {
	
	private String status;
	
	private String message;
	
	private T data;
	
	public ModelTemplate(Exception ex) {
		this.status = "500";
		this.message = ex.getMessage();
	}

	public ModelTemplate(T data) {
		this.status = "000";
		this.data = data;
	}
	
	public ModelTemplate(ServiceException ex) {
		this.status = ex.getCode();
		this.message = ex.getMessage();
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

}
