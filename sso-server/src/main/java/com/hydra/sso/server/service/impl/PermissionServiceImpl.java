package com.hydra.sso.server.service.impl;

import com.hydra.sso.client.api.model.SsoPermission;
import com.hydra.sso.server.dao.PermissionDao;
import com.hydra.sso.server.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yahto on 03/01/2018
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<SsoPermission> findPermissionListById(String applicationCode, Long userId) {
        return permissionDao.findPermissionListById(applicationCode,userId);
    }
}
