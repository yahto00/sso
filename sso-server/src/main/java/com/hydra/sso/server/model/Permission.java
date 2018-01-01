package com.hydra.sso.server.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限实体类
 *
 * @author yahto
 * 23/12/2017 10:54 PM
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 8241639305825716041L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限访问URL
     */
    private String url;
    /**
     * 权限创建时间
     */
    private Date createTime;
    /**
     * 权限描述
     */
    private String description;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
