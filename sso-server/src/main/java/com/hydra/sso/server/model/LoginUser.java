package com.hydra.sso.server.model;

import java.io.Serializable;

/**
 * @author yahto
 * 01/01/2018 3:57 PM
 */
public class LoginUser implements Serializable {

    private static final long serialVersionUID = -7630721302219127796L;

    // 登录成功ID
    private Long id;
    // 登录成功用户名
    private String account;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoginUser other = (LoginUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
