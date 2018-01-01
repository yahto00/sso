package com.hydra.sso.server.service.impl;

import com.hydra.sso.client.model.Result;
import com.hydra.sso.client.model.ResultCode;
import com.hydra.sso.server.dao.UserDao;
import com.hydra.sso.server.model.User;
import com.hydra.sso.server.service.ApplicationService;
import com.hydra.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author yahto
 * 23/12/2017 11:02 PM
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ApplicationService applicationService;

    @Override
    public Result login(String ipAddress, String account, String applicationCode, String password) {
        Result result = Result.createSuccessResult();
        User user = userDao.findUserByAccount(account);
        if (user == null) {
            result.setCode(ResultCode.ERROR).setMessage("登录名不存在");
        } else if (!password.equals(user.getPassword())) {
            result.setCode(ResultCode.ERROR).setMessage("密码不正确");
        } else {
            Set<String> set = applicationService.findApplicationCodeByUserId(user.getId());
        }
        return null;
    }
}
