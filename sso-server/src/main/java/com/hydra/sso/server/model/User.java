package com.hydra.sso.server.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author yahto
 * 23/12/2017 10:59 PM
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1386857259902086586L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 登录名
     */
    private String account;
    /**
     * 密码(加密)
     */
    private String password;
    /**
     * 最后一次登陆ip
     */
    private String lastLoginIp;
    /**
     * 最后一次登陆时间
     */
    private Date lastLoginTime;
    /**
     * 登陆次数
     */
    private Long loginCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }
}
