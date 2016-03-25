package com.goldbao.bankroll.vo.manage;

/**
 * @author shuyu.fang
 */
public class UserApplyRealResult {

    private Long id;

    private String username;

    private String cardId;
    private String realName;
    private String cardIdPhoto1;
    
    private String cardIdPhoto2;
    
    private String cardIdPhoto3;
    
    private Integer status;

    private String addTime;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
