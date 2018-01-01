package com.hydra.sso.server.service.impl;

import com.hydra.sso.client.api.model.SsoPermission;
import com.hydra.sso.client.api.model.SsoUser;
import com.hydra.sso.client.api.service.AuthorizationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yahto
 * 23/12/2017 11:03 PM
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public boolean validate(String token) {
        return false;
    }

    @Override
    public SsoUser getAuthInfo(String token) {
        return null;
    }

    @Override
    public List<SsoPermission> getPermissionList(String token, String applicationCode) {
        return null;
    }
}
