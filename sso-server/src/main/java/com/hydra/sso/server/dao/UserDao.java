package com.hydra.sso.server.dao;

import com.hydra.sso.server.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yahto
 * 23/12/2017 11:03 PM
 */
@Repository
public interface UserDao {
    User findUserByAccount(@Param("account") String account);
}
