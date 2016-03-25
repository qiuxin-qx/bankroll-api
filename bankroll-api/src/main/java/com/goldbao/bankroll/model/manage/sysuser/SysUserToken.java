package com.goldbao.bankroll.model.manage.sysuser;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.goldbao.bankroll.model.Model;

/**
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_sys_user_token")
@AttributeOverride(name = "id", column = @Column(name = "token_id"))
public class SysUserToken extends Model implements Serializable {

	private static final long serialVersionUID = 259949674952974775L;

	private String token;

    @Column(name="expire_time")
    private Date expireTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SysUser user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }
}
