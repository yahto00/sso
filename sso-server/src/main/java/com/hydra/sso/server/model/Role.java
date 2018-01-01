package com.hydra.sso.server.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体类
 *
 * @author yahto
 * 23/12/2017 10:55 PM
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 6186776188102860339L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 角色创建日期
     */
    private Date createTime;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
