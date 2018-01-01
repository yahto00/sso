package com.hydra.sso.client.util;

import com.hydra.sso.client.api.model.SsoPermission;
import com.hydra.sso.client.api.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 当前应用权限初始化
 *
 * @author yahto
 * 23/12/2017 3:42 PM
 */
public class ApplicationPermissionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPermissionUtils.class);
    // 应用所有权限URL
    private static Set<String> applicationPermissionSet = null;
    // 并发监控
    private static Object monitor = new Object();

    /**
     * 1.应用初始化，获取应用所有的菜单及权限 2.权限有变动修改，JMS通知重新加载
     */
    public static void initApplicationPermissions(AuthorizationService authenticationRpcService, String applocationCode) {
        List<SsoPermission> ssoPermissionList = null;
        try {
            ssoPermissionList = authenticationRpcService.getPermissionList(null, applocationCode);
        } catch (Exception e) {
            ssoPermissionList = new ArrayList<SsoPermission>(0);
            LOGGER.error("无法连接到单点登录鉴权系统,请检查配置sso.server.url", e);
        }

        synchronized (monitor) {
            applicationPermissionSet = new HashSet<String>();
            applicationPermissionSet = new HashSet<String>();
            for (SsoPermission menu : ssoPermissionList) {
                if (StringUtils.isNotBlank(menu.getUrl())) {
                    applicationPermissionSet.add(menu.getUrl());
                }
            }
        }
    }

    public static Set<String> getApplicationPermissionSet() {
        synchronized (monitor) {
            return applicationPermissionSet;
        }
    }
}
