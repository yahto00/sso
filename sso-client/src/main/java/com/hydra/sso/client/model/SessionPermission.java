package com.hydra.sso.client.model;

import java.io.Serializable;
import java.util.Set;

/**
 * @author yahto
 * 23/12/2017 3:25 PM
 */
public class SessionPermission implements Serializable {

    private static final long serialVersionUID = -2228002655439420148L;

    /**
     * 用户权限集合
     */
    private Set<String> permissionSet;
    /**
     * 用户没有的权限
     */
    private String noPermissions;

    public Set<String> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<String> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public String getNoPermissions() {
        return noPermissions;
    }

    public void setNoPermissions(String noPermissions) {
        this.noPermissions = noPermissions;
    }
}
