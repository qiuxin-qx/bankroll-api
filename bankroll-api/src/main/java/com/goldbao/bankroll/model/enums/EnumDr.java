package com.goldbao.bankroll.model.enums;

/**
 * 删除标识
 * @author shuyu.fang
 *
 */
public enum EnumDr {
	/**
	 * 正常
	 */
	NORMAL(0),
	/**
	 * 删除
	 */
	DELETED(1);
	
	private Integer dr;

	EnumDr(Integer dr) {
		this.dr = dr;
	}
	
	public Integer getDr() {
		return this.dr;
	}
}
