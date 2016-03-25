package com.goldbao.bankroll.model.user;


import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumValidateCodePurpose;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 手机校验码
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_phone_validate_code")
@AttributeOverride(name="id", column = @Column(name = "code_id"))
public class PhoneValidateCode extends Model implements Serializable {
	private static final long serialVersionUID = -2872144359812837758L;
	
	private String phone;
    private String code;
    private Integer used;
    @Column(name = "used_time")
    private Date usedTime;
    private EnumValidateCodePurpose purpose;

    /**
     * 手机号码
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * 验证码
     */
    public String getCode() {
        return code;
    }
    /**
     * 验证码
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * 是否使用过, 0为未使用，1为已使用
     */
    public Integer getUsed() {
        return used;
    }
    /**
     * 是否使用过, 0为未使用，1为已使用
     */
    public void setUsed(Integer used) {
        this.used = used;
    }
    /**
     * 使用时间
     */
    public Date getUsedTime() {
        return usedTime;
    }
    /**
     * 使用时间
     */
    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }
    /**
     * 使用目的
     */
    public EnumValidateCodePurpose getPurpose() {
        return purpose;
    }
    /**
     * 使用目的
     */
    public void setPurpose(EnumValidateCodePurpose purpose) {
        this.purpose = purpose;
    }
}
