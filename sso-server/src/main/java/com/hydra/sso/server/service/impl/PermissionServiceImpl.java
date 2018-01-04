package com.hydra.sso.server.service.impl;

import com.hydra.sso.client.api.model.SsoPermission;
import com.hydra.sso.client.model.Result;
import com.hydra.sso.client.model.ResultCode;
import com.hydra.sso.server.dao.PermissionDao;
import com.hydra.sso.server.model.Permission;
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
        return permissionDao.findPermissionListById(applicationCode, userId);
    }

    @Override
    public Result addPermissionByRoleId(Long applicationId, Permission permission, Long roleId) {
        Result result = Result.createSuccessResult();
        int res = permissionDao.insertSelective(permission);
        if (res < 1) {
            return result.setCode(ResultCode.ERROR).setMessage("添加失败");
        }
        res = permissionDao.insertRolePermission(applicationId, roleId, permission.getId());
        if (res < 1) {
            return result.setCode(ResultCode.ERROR).setMessage("添加失败");
        }
        return result;
    }
}
