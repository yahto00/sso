package com.hydra.sso.server.dao;

import com.hydra.sso.client.api.model.SsoPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yahto on 03/01/2018
 */
@Repository
public interface PermissionDao {
    List<SsoPermission> findPermissionListById(@Param("applicationCode") String applicationCode,
                                               @Param("userId") Long userId);
}
