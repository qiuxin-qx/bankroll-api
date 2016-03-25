package com.goldbao.bankroll.model.trade;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.user.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户的银行卡信息
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_user_bank")
@AttributeOverride(name = "id", column = @Column(name = "user_bank_id"))
public class UserBank extends Model implements Serializable {
    @Column(name = "bank_id")
    private String bankId;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    /**
     * 银行编号，参考中金接口文档
     */
    public String getBankId() {
        return bankId;
    }
    /**
     * 银行编号，参考中金接口文档
     */
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    /**
     * 银行卡持有人姓名
     */
    public String getAccountName() {
        return accountName;
    }
    /**
     * 银行卡持有人姓名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * 银行卡号
     */
    public String getAccountNumber() {
        return accountNumber;
    }
    /**
     * 银行卡号
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    /**
     * 分支行名称
     */
    public String getBranchName() {
        return branchName;
    }
    /**
     * 分支行名称
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    /**
     * 分支行所属省
     */
    public String getProvince() {
        return province;
    }
    /**
     * 分支行所属省
     */
    public void setProvince(String province) {
        this.province = province;
    }
    /**
     * 分支行所属市
     */
    public String getCity() {
        return city;
    }
    /**
     * 分支行所属市
     */
    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
