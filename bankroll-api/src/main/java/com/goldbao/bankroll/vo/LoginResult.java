package com.goldbao.bankroll.vo;

public class LoginResult {
    private Long id;
    private String username;
    private String token;
    private Integer status;

    private String mobilephone;

    private Integer verifiedMobilephone;

    private String email;

    private Integer verifiedEmail;

    private Integer verifiedCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Integer getVerifiedMobilephone() {
        return verifiedMobilephone;
    }

    public void setVerifiedMobilephone(Integer verifiedMobilephone) {
        this.verifiedMobilephone = verifiedMobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getVerifiedEmail() {
        return verifiedEmail;
    }

    public void setVerifiedEmail(Integer verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }

    public Integer getVerifiedCard() {
        return verifiedCard;
    }

    public void setVerifiedCard(Integer verifiedCard) {
        this.verifiedCard = verifiedCard;
    }
}
