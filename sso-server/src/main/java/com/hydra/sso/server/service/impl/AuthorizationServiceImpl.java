package com.hydra.sso.server.service.impl;

import com.hydra.sso.client.api.model.SsoPermission;
import com.hydra.sso.client.api.model.SsoUser;
import com.hydra.sso.client.api.service.AuthorizationService;
import com.hydra.sso.client.util.StringUtils;
import com.hydra.sso.server.common.token.TokenManager;
import com.hydra.sso.server.model.LoginUser;
import com.hydra.sso.server.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yahto
 * 23/12/2017 11:03 PM
 */
public class AuthorizationServiceImpl implements AuthorizationService {

    @Resource
    private TokenManager tokenManager;
    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean validate(String token) {
        return tokenManager.validate(token) == null;
    }

    @Override
    public SsoUser getAuthInfo(String token) {
        LoginUser loginUser = tokenManager.validate(token);
        if (loginUser != null) {
            return new SsoUser(loginUser.getId(), loginUser.getAccount());
        }
        return null;
    }

    @Override
    public List<SsoPermission> getPermissionList(String token, String applicationCode) {
        if (StringUtils.isBlank(token)) {
            return permissionService.findPermissionListById(applicationCode, null);
        }
        LoginUser loginUser = tokenManager.validate(token);
        if (loginUser != null) {
            return permissionService.findPermissionListById(applicationCode, loginUser.getId());
        }
        return null;
    }
}
