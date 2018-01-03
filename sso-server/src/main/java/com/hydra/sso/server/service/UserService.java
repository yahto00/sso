package com.hydra.sso.server.service;

import com.hydra.sso.client.model.Result;
import com.hydra.sso.server.model.User;

/**
 * @author yahto
 * 23/12/2017 11:03 PM
 */
public interface UserService {
    Result login(String ipAddress, String account, String applicationCode, String password);

    User findUserByAccount(String account);

    Result addUser(User user);
}
