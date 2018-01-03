package com.hydra.sso.server.dao;

import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author yahto
 * 01/01/2018 4:50 PM
 */
@Repository
public interface ApplicationDao {

    Set<String> findApplicationCodeByUserId(Long userId);
}
