package com.hydra.sso.server.web;

import com.hydra.sso.client.model.Result;
import com.hydra.sso.server.model.User;
import com.hydra.sso.server.provider.PasswordProvider;
import com.hydra.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by yahto on 03/01/2018
 */
@RequestMapping("/admin/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser.ajax", method = RequestMethod.POST)
    @ResponseBody
    public Result addUser(@RequestParam("name") String name,
                          @RequestParam("account") String account,
                          @RequestParam("password") String password) {
        User user = new User();
        user.setName(name);
        user.setAccount(account);
        user.setPassword(PasswordProvider.encrypt(password));
        user.setCreateTime(new Date());
        return userService.addUser(user);
    }
}
