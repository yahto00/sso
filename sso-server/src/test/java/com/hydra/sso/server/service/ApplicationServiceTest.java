package com.hydra.sso.server.service;

import com.hydra.sso.server.model.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by yahto on 06/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ApplicationServiceTest {
    @Autowired
    private ApplicationService applicationService;

    @Test
    public void pageTest() {
        List<Application> applicationList = applicationService.getApplicationByPage(1, 1);
        System.out.println(applicationList.size());
    }
}
