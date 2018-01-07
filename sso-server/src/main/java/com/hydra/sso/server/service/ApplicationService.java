package com.hydra.sso.server.service;

import com.hydra.sso.client.model.Result;
import com.hydra.sso.server.model.Application;

import java.util.Set;

/**
 * @author yahto
 * 01/01/2018 4:49 PM
 */
public interface ApplicationService {
    Set<String> findApplicationCodeByUserId(Long userId);

    String findApplicationCodeById(Long applicationId);

    Result addApplication(Application application);
}
