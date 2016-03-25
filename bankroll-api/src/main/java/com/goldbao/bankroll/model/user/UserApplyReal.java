package com.goldbao.bankroll.model.user;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumVerified;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 申请实名认证
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_user_apply_real")
@AttributeOverride(name = "id", column = @Column(name = "apply_id"))
public class UserApplyReal extends Model implements Serializable {

	private static final long serialVersionUID = -7178493581481021852L;

	@OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "card_id")
    private String cardId;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "card_id_photo_1")
    private String cardIdPhoto1;
    @Column(name = "card_id_photo_2")
    private String cardIdPhoto2;
    @Column(name = "card_id_photo_3")
    private String cardIdPhoto3;
    @Column(name = "verified_card")
    private EnumVerified status;

    /**
     * 申请人
     */
    public User getUser() {
        return user;
    }
    /**
     * 申请人
     */
    public void setUser(User user) {
        this.user = user;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 身份证正面照地址
     */
    public String getCardIdPhoto1() {
        return cardIdPhoto1;
    }
    /**
     * 身份证正面照地址
     */
    public void setCardIdPhoto1(String cardIdPhoto1) {
        this.cardIdPhoto1 = cardIdPhoto1;
    }
    /**
     * 身份证背面照地址
     */
    public String getCardIdPhoto2() {
        return cardIdPhoto2;
    }
    /**
     * 身份证背面照地址
     */
    public void setCardIdPhoto2(String cardIdPhoto2) {
        this.cardIdPhoto2 = cardIdPhoto2;
    }
    /**
     * 身份证全身照地址
     */
    public String getCardIdPhoto3() {
        return cardIdPhoto3;
    }
    /**
     * 身份证全身照地址
     */
    public void setCardIdPhoto3(String cardIdPhoto3) {
        this.cardIdPhoto3 = cardIdPhoto3;
    }
    /**
     * 审核状态
     */
    public EnumVerified getStatus() {
        return status;
    }
    /**
     * 审核状态
     */
    public void setStatus(EnumVerified status) {
        this.status = status;
    }
}
