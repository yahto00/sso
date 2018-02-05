package com.hydra.sso.server.service;

import com.hydra.sso.client.api.model.SsoPermission;
import com.hydra.sso.client.model.Result;
import com.hydra.sso.server.model.Permission;

import java.util.List;

/**
 * Created by yahto on 03/01/2018
 */
public interface PermissionService {
    List<SsoPermission> findPermissionListById(String applicationCode, Long userId);

    Result addPermissionByRoleId(Long applicationId, Permission permission, Long roleId);

    List<SsoPermission> findPermissionListByApplicationCode(String applicationCode);
}
