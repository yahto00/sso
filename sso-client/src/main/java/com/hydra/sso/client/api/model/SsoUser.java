package com.hydra.sso.client.api.model;

import java.io.Serializable;

/**
 * @author yahto
 * 23/12/2017 2:48 PM
 */
public class SsoUser implements Serializable {

    private static final long serialVersionUID = -7214105010855304819L;

    private Long id;

    private String account;

    public SsoUser(Long id, String account) {
        super();
        this.id = id;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
