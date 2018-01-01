package com.hydra.sso.server.service.impl;

import com.hydra.sso.server.dao.ApplicationDao;
import com.hydra.sso.server.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }
}
