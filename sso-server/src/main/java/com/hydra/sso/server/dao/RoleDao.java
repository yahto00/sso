package com.hydra.sso.server.dao;

import com.hydra.sso.server.model.Role;
import com.hydra.sso.server.model.RoleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int addUserRole(@Param("userId") Long userId,
                    @Param("applicationId") Long applicationId,
                    @Param("roleIdsList") List<Long> roleIdsList);

    List<Role> queryRoleByApplicationId(Long applicationId);
}