package com.goldbao.bankroll.model.manage.sysuser;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumUserStatus;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_sys_user")
@AttributeOverride(name = "id", column = @Column(name="sysuser_id"))
public class SysUser extends Model implements Serializable {
	private static final long serialVersionUID = 7816165895242353272L;

	private String username; // 登录名

    private String password;

    private String salt;

    private EnumUserStatus status; // 后台用户状态，可以和前台共享一套？

    private String realname;

    // TODO 后台用户暂时简单设计，后续添加详细信息，组，角色，部门，权限划分等

    /**
     * 登录用户名
     */
    public String getUsername() {
        return username;
    }
    /**
     * 登录用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * 用户密码（密文），加密规则为（md5(password+salt))
     */
    public String getPassword() {
        return password;
    }
    /**
     * 用户密码（密文），加密规则为（md5(password+salt))
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 给密码加盐，
     * @see com.goldbao.bankroll.model.user.User salt
     */
    public String getSalt() {
        return salt;
    }
    /**
     * 给密码加盐，
     * @see com.goldbao.bankroll.model.user.User salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 状态
     */
    public EnumUserStatus getStatus() {
        return status;
    }
    /**
     * 状态
     */
    public void setStatus(EnumUserStatus status) {
        this.status = status;
    }
    /**
     * 真实姓名
     */
    public String getRealname() {
        return realname;
    }
    /**
     * 真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }
}
