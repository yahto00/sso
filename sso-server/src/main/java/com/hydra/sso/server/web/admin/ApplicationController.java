package com.hydra.sso.server.web.admin;

import com.hydra.sso.client.model.Result;
import com.hydra.sso.client.model.ResultCode;
import com.hydra.sso.server.model.Application;
import com.hydra.sso.server.provider.IdProvider;
import com.hydra.sso.server.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by yahto on 06/01/2018
 */
@RequestMapping("/admin/application")
@Controller
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    /**
     * 添加应用
     *
     * @param applicationName
     * @return
     */
    @RequestMapping("/addApplication.ajax")
    @ResponseBody
    public Result addApplication(@RequestParam("applicationName") String applicationName) {
        Application application = new Application();
        application.setName(applicationName);
        application.setCode(IdProvider.createUUIDId());
        application.setCreateTime(new Date());
        return applicationService.addApplication(application);
    }

    @RequestMapping(value = "/getApplicationByPage.ajax", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result getApplicationByPage(@RequestParam("start") Integer start,
                                       @RequestParam("pageSize") Integer pageSize) {
        List<Application> applicationList = applicationService.getApplicationByPage(start, pageSize);
        if (CollectionUtils.isEmpty(applicationList)) {
            return Result.create(ResultCode.ERROR).setMessage("没有数据");
        }
        return Result.createSuccessResult().setData(applicationList);
    }
}
