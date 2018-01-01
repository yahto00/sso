package com.hydra.sso.client.model;

import java.io.Serializable;

/**
 * 已登录用户信息
 *
 * @author yahto
 * 23/12/2017 2:14 PM
 */
public class SessionUser implements Serializable {

    private static final long serialVersionUID = 3354469148464585078L;

    /**
     * 登陆用户访问token
     */
    private String token;

    /**
     * 登陆账户
     */
    private String account;

    public SessionUser() {
        super();
    }

    public SessionUser(String token, String account) {
        super();
        this.token = token;
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
