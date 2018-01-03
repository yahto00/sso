package com.hydra.sso.server.service.impl;

import com.hydra.sso.client.model.Result;
import com.hydra.sso.client.model.ResultCode;
import com.hydra.sso.server.dao.UserDao;
import com.hydra.sso.server.model.User;
import com.hydra.sso.server.service.ApplicationService;
import com.hydra.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
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
        User user = findUserByAccount(account);
        if (user == null) {
            result.setCode(ResultCode.ERROR).setMessage("登录名不存在");
        } else if (!password.equals(user.getPassword())) {
            result.setCode(ResultCode.ERROR).setMessage("密码不正确");
        } else {
            Set<String> set = applicationService.findApplicationCodeByUserId(user.getId());
            if (CollectionUtils.isEmpty(set)) {
                result.setCode(ResultCode.ERROR).setMessage("不存在可操作的应用");
            } else if (!set.contains(applicationCode)) {
                result.setCode(ResultCode.ERROR).setMessage("没有应用操作权限");
            } else {
                user.setLastLoginIp(ipAddress);
                user.setLastLoginTime(new Date());
                userDao.updateLoginInfo(user);
                result.setData(findUserByAccount(account));
            }
        }
        return result;
    }

    @Override
    public User findUserByAccount(String account) {
        return userDao.findUserByAccount(account);
    }

    @Override
    public Result addUser(User user) {
        Result result = Result.createSuccessResult();
        int res = userDao.insertSelective(user);
        if (res < 1) {
            result.setCode(ResultCode.ERROR).setMessage("创建用户失败");
        }
        return result;
    }


}
