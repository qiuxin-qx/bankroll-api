package com.goldbao.pay;

/**
 * 银行卡信息 - 目前按照中金模式实现
 * @author shuyu.fang
 */
public class BankAccount2 {
    private String bankID;
    private String accountName;
    private String accountNumber;
    private String branchName;
    private String province;
    private String city;

    private String cardId;
//    private String phoneNumber;

    /**
     * 银行编号，目前用中金的编号，如果后续有其他支付方式，
     * 可以做一个我们自己的银行编号体系，然后映射到不同的支付方式上去
     */
    public String getBankID() {
        return bankID;
    }
    /**
     * 银行编号，目前也是中金的编号，如果后续有其他支付方式，
     * 可以做一个我们自己的银行编号体系，然后映射到不同的支付方式上去
     */
    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    /**
     * 账户名称
     */
    public String getAccountName() {
        return accountName;
    }
    /**
     * 账户名称
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    /**
     * 账户号码
     */
    public String getAccountNumber() {
        return accountNumber;
    }
    /**
     * 账户号码
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
     * 分支行省份
     */
    public String getProvince() {
        return province;
    }
    /**
     * 分支行省份
     */
    public void setProvince(String province) {
        this.province = province;
    }
    /**
     * 分支行城市
     */
    public String getCity() {
        return city;
    }
    /**
     * 分支行城市
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * 开户手机号码
     */
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
    /**
     * 开户手机号码
     */
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }


    /**
     * 身份证号码
     */
    public String getCardId() {
        return cardId;
    }
    /**
     * 身份证号码
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
