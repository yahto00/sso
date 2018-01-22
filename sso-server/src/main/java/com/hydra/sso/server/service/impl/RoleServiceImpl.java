package com.hydra.sso.server.service.impl;

import com.hydra.sso.client.excecption.ServiceException;
import com.hydra.sso.client.model.Result;
import com.hydra.sso.client.model.ResultCode;
import com.hydra.sso.server.dao.RoleDao;
import com.hydra.sso.server.model.Role;
import com.hydra.sso.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yahto on 04/01/2018
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Result addRole(Role role) {
        Result result = Result.createSuccessResult();
        int res = roleDao.insertSelective(role);
        if (res < 1) {
            result.setCode(ResultCode.ERROR).setMessage("添加角色失败");
        }
        return result.setData(role);
    }

    @Transactional
    @Override
    public Result addUserRole(Long userId, Long applicationId, List<Long> roleIdsList) {
        Result result = Result.createSuccessResult();
        int res = roleDao.addUserRole(userId, applicationId, roleIdsList);
        if (res < roleIdsList.size()) {
            throw new ServiceException();
        }
        return result;
    }

    @Override
    public Result queryRoleByApplicationId(Long applicationId) {
        List<Role> roleList = roleDao.queryRoleByApplicationId(applicationId);
        return Result.createSuccessResult().setData(roleList);
    }
}
