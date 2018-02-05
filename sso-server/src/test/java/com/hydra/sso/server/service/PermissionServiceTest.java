package com.hydra.sso.server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yahto on 05/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PermissionServiceTest {
    @Autowired
    private PermissionService permissionService;

    @Test
    public void test() {
        System.out.println(permissionService.findPermissionListByApplicationCode("ssoKKK"));
    }
}
