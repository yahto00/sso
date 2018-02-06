package com.hydra.sso.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.hydra.sso.client.model.Result;
import com.hydra.sso.client.model.ResultCode;
import com.hydra.sso.server.dao.ApplicationDao;
import com.hydra.sso.server.model.Application;
import com.hydra.sso.server.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author yahto
 * 01/01/2018 4:49 PM
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public Set<String> findApplicationCodeByUserId(Long userId) {
        return applicationDao.findApplicationCodeByUserId(userId);
    }

    @Override
    public String findApplicationCodeById(Long applicationId) {
        return applicationDao.selectByPrimaryKey(applicationId).getCode();
    }

    @Override
    public Result addApplication(Application application) {
        Result result = Result.createSuccessResult();
        int res = applicationDao.insertSelective(application);
        if (res < 1) {
            return result.setCode(ResultCode.ERROR).setMessage("添加应用失败");
        }
        return result.setData(application);
    }

    @Override
    public List<Application> getApplicationByPage(Integer start, Integer pageSize) {
        PageHelper.startPage(start, pageSize, true);
        List<Application> applicationList = applicationDao.getAllApplication();
        return applicationList;
    }
}
