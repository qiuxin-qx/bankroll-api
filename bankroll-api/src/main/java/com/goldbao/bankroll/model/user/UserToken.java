package com.goldbao.bankroll.model.user;

import com.goldbao.bankroll.model.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 调用接口授权表
 * @author John
 *
 */
@Entity
@Table(name = "bk_user_token")
@AttributeOverride(name = "id", column = @Column(name = "token_id"))
public class UserToken extends Model implements Serializable {

	private static final long serialVersionUID = -8370982505289028127L;

	private String token;

	@Column(name="expire_time")
	private Date expireTime;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/**
	 * 用户请求接口的授权值
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 用户请求接口的授权值
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 授权过期时间
	 */
	public Date getExpireTime() {
		return expireTime;
	}

	/**
	 * 授权过期时间
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * 该授权所属用户
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * 该授权所属用户
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
