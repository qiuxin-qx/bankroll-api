package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * homs 登录
 */
public class HomsLoginResult {

    @JsonProperty("user_token")
    private String userToken;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
