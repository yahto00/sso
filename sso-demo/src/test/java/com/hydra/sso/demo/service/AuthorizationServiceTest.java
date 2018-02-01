package com.hydra.sso.demo.service;

import com.hydra.sso.client.api.service.AuthorizationService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yahto on 31/01/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AuthorizationServiceTest {
    @Resource
    private AuthorizationService authorizationService;
}
