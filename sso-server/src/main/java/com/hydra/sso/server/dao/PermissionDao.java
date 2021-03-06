package com.hydra.sso.server.dao;

import com.hydra.sso.client.api.model.SsoPermission;
import com.hydra.sso.server.model.Permission;
import com.hydra.sso.server.model.PermissionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {
    int countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<SsoPermission> findPermissionListById(@Param("applicationCode") String applicationCode,
                                               @Param("userId") Long userId);

    int insertRolePermission(@Param("applicationId") Long applicationId,
                             @Param("roleId") Long roleId,
                             @Param("permissionId") Long permissionId);

    List<SsoPermission> findPermissionListByApplicationCode(@Param("applicationCode") String applicationCode);

    int insertApplicationPermission(@Param("applicationId") Long applicationId,
                                    @Param("permissionId") Long permissionId);
}