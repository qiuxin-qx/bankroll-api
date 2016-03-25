package com.goldbao.bankroll.model.user;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumUserStatus;
import com.goldbao.bankroll.model.enums.EnumVerified;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户帐号基本信息
 *
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_user")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends Model implements Serializable {
	private static final long serialVersionUID = -6008209455702412341L;

	private String username;

    private String mobilephone;
    @Column(name = "verified_mobilephone")
    private EnumVerified verifiedMobilephone;

    private String password;

    @Column(name = "trade_password")
    private String tradePassword;
    private String email;
    @Column(name = "verified_email")
    private EnumVerified verifiedEmail;
    @Column(name = "real_name")
    private String realName;
    @Column(name = "card_id")
    private String cardId;

    @Column(name = "card_id_photo_1")
    private String cardIdPhoto1;
    @Column(name = "card_id_photo_2")
    private String cardIdPhoto2;
    @Column(name = "card_id_photo_3")
    private String cardIdPhoto3;
    @Column(name = "verified_card")
    private EnumVerified verifiedCard;

    private String salt;

//    @Column(name = "register_time")
//    private Date registerTime;
    @Column(name = "register_ip")
    private String registerIp;
    @Column(name = "user_status")
    private EnumUserStatus userStatus;
    @Column(name = "last_login_time")
    private Date lastLoginTime;
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    @Column(name = "head_portait")
    private String headPortait; // 头像

    @OneToOne(mappedBy="user")
    private UserFund userFund;

    /**
     * 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 用户手机号码
     */
    public String getMobilephone() {
        return mobilephone;
    }
    /**
     * 用户手机号码
     */
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
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
     * 交易密码，加密规则同@password
     */
    public String getTradePassword() {
        return tradePassword;
    }
    /**
     * 交易密码，加密规则同@password
     */
    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }
    /**
     * email，可用于登录
     */
    public String getEmail() {
        return email;
    }
    /**
     * email，可用于登录
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 真实姓名，实名认证
     */
    public String getRealName() {
        return realName;
    }
    /**
     * 真实姓名，实名认证
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }
    /**
     * 身份证号，实名认证
     */
    public String getCardId() {
        return cardId;
    }
    /**
     * 身份证号，实名认证
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    /**
     * 身份证号，实名认证
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCardIdPhoto1() {
        return cardIdPhoto1;
    }

    public void setCardIdPhoto1(String cardIdPhoto1) {
        this.cardIdPhoto1 = cardIdPhoto1;
    }

    public String getCardIdPhoto2() {
        return cardIdPhoto2;
    }

    public void setCardIdPhoto2(String cardIdPhoto2) {
        this.cardIdPhoto2 = cardIdPhoto2;
    }

    public String getCardIdPhoto3() {
        return cardIdPhoto3;
    }

    public void setCardIdPhoto3(String cardIdPhoto3) {
        this.cardIdPhoto3 = cardIdPhoto3;
    }

    /**
     * 用户密码加密辅助
     */
    public String getSalt() {
        return salt;
    }
    /**
     * 用户密码加密辅助
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 注册时间
     */
//    public Date getRegisterTime() {
//        return registerTime;
//    }
    /**
     * 注册时间
     */
//    public void setRegisterTime(Date registerTime) {
//        this.registerTime = registerTime;
//    }
    /**
     * 用户状态（枚举）
     */
    public EnumUserStatus getUserStatus() {
        return userStatus;
    }
    /**
     * 用户状态（枚举）
     */
    public void setUserStatus(EnumUserStatus userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 注册IP
     */
    public String getRegisterIp() {
        return registerIp;
    }
    /**
     * 注册IP
     */
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }
    /**
     * 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    /**
     * 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    /**
     * 最后登录IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }
    /**
     * 最后登录IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

	public UserFund getUserFund() {
		return userFund;
	}

	public void setUserFund(UserFund userFund) {
		this.userFund = userFund;
	}

    /**
     * 手机号码是否通过验证
     */
    public EnumVerified getVerifiedMobilephone() {
        return verifiedMobilephone;
    }
    /**
     * 手机号码是否通过验证
     */
    public void setVerifiedMobilephone(EnumVerified verifiedMobilephone) {
        this.verifiedMobilephone = verifiedMobilephone;
    }
    /**
     * email是否通过验证
     */
    public EnumVerified getVerifiedEmail() {
        return verifiedEmail;
    }
    /**
     * email是否通过验证
     */
    public void setVerifiedEmail(EnumVerified verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }
    /**
     * 身份证（实名）是否通过验证
     */
    public EnumVerified getVerifiedCard() {
        return verifiedCard;
    }
    /**
     * 身份证（实名）是否通过验证
     */
    public void setVerifiedCard(EnumVerified verifiedCard) {
        this.verifiedCard = verifiedCard;
    }
    /**
     * 头像
     */
    public String getHeadPortait() {
        return headPortait;
    }
    /**
     * 头像
     */
    public void setHeadPortait(String headPortait) {
        this.headPortait = headPortait;
    }
}
