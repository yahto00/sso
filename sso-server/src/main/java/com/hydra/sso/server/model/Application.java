package com.hydra.sso.server.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用实体类
 *
 * @author yahto
 * 23/12/2017 10:51 PM
 */
public class Application implements Serializable {

    private static final long serialVersionUID = 4134291731122651L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 应用名称
     */
    private String name;
    /**
     * 应用创建时间
     */
    private Date createTime;
    /**
     * 应用编码
     */
    private String code;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
