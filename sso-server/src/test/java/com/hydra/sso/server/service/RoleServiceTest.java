package com.hydra.sso.server.service;

import com.alibaba.fastjson.JSON;
import com.hydra.sso.client.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by yahto on 04/01/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void queryRoleByApplicationId() {
        Result result = roleService.queryRoleByApplicationId(1L);
        System.out.println(JSON.toJSONString(result));
    }
}
