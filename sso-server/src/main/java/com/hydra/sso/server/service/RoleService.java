package com.hydra.sso.server.service;

import com.hydra.sso.client.model.Result;
import com.hydra.sso.server.model.Role;

import java.util.List;

/**
 * Created by yahto on 04/01/2018
 */
public interface RoleService {
    Result addRole(Role role);

    Result addUserRole(Long userId, Long applicationId, List<Long> roleIdsList);

    Result queryRoleByApplicationId(Long applicationId);
}
