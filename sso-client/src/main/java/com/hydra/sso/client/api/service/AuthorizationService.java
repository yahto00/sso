package com.hydra.sso.client.api.service;

import com.hydra.sso.client.api.model.SsoPermission;
import com.hydra.sso.client.api.model.SsoUser;

import java.util.List;

/**
 * dubbo服务接口
 *
 * @author yahto
 * 23/12/2017 2:48 PM
 */
public interface AuthorizationService {
    /**
     * 验证是否已经登录
     *
     * @param token
     * @return
     */
    boolean validate(String token);

    /**
     * 根据登录的Token和应用编码获取授权用户信息
     *
     * @param token
     * @return
     */
    SsoUser getAuthInfo(String token);

    /**
     * 根据token和application查询访问权限
     *
     * @param token
     * @param applicationCode
     * @return
     */
    List<SsoPermission> getPermissionList(String token, String applicationCode);
}
