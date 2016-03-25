package com.goldbao.bankroll.model.homs;

import com.goldbao.bankroll.model.Model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户homs账户信息
 */
@Entity
@Table(name = "bk_homs_user")
@AttributeOverride(name = "id", column = @Column(name = "user_homs_id"))
public class HomsUser extends Model implements Serializable {
	private static final long serialVersionUID = -4276388053896261221L;

	@Column(name = "operator_no")
	private String operatorNo;

	private String password;
	@Column(name = "user_token")
	private String userToken;
	@Column(name = "last_login_time")
	private Date lastLoginTime;

	@Column(name = "last_login_ip")
	private String lastLoginIp;
	@Column(name = "last_login_mac")
	private String lastLoginMac;


	/**
	 * homs操作员编号-用于登录
	 */
	public String getOperatorNo() {
		return operatorNo;
	}
	/**
	 * homs操作员编号-用于登录
	 */
	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}
	/**
	 * homs操作员密码-用于登录
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * homs操作员密码-用于登录
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * homs操作员操作token-用于homs所有的接口调用
	 */
	public String getUserToken() {
		return userToken;
	}
	/**
	 * homs操作员操作token-用于homs所有的接口调用
	 */
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	/**
	 * homs操作员最后登录时间
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	/**
	 * homs操作员最后登录时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * homs操作员最后登录IP
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	/**
	 * homs操作员最后登录IP
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	/**
	 * homs操作员最后登录MAC
	 */
	public String getLastLoginMac() {
		return lastLoginMac;
	}
	/**
	 * homs操作员最后登录MAC
	 */
	public void setLastLoginMac(String lastLoginMac) {
		this.lastLoginMac = lastLoginMac;
	}
}
