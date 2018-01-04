package com.hydra.sso.server.web.admin;

import com.hydra.sso.client.excecption.ServiceException;
import com.hydra.sso.client.model.Result;
import com.hydra.sso.client.model.ResultCode;
import com.hydra.sso.server.model.Role;
import com.hydra.sso.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yahto on 04/01/2018
 */
@RequestMapping("/admin/role")
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 创建角色
     *
     * @param roleName
     * @param description
     * @return
     */
    @RequestMapping(value = "/addRole.ajax", method = RequestMethod.POST)
    @ResponseBody
    public Result addRole(@RequestParam("roleName") String roleName,
                          @RequestParam(value = "description", required = false) String description) {
        Role role = new Role();
        role.setName(roleName);
        role.setDescription(description);
        role.setCreateTime(new Date());
        return roleService.addRole(role);
    }

    /**
     * 用户添加角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping(value = "/addUserRole.ajax", method = RequestMethod.POST)
    @ResponseBody
    public Result addUserRole(@RequestParam("userId") Long userId,
                              @RequestParam("applicationId") Long applicationId,
                              @RequestParam("roleIds") String roleIds) {
        String[] roleIdsArr = roleIds.split(",");
        List<Long> roleIdsList = new ArrayList<>();
        for (int i = 0; i < roleIdsArr.length; i++) {
            roleIdsList.add(Long.valueOf(roleIdsArr[i]));
        }
        try {
            return roleService.addUserRole(userId, applicationId, roleIdsList);
        } catch (ServiceException e) {
            return Result.create(ResultCode.ERROR).setMessage(e.getMessage());
        }
    }

    /**
     * 根据应用Id查询应用所有的角色
     *
     * @param applicationId
     * @return
     */
    @RequestMapping("/queryRoleByApplicationId.ajax")
    @ResponseBody
    public Result queryRoleByApplicationId(@RequestParam("applicationId") Long applicationId) {
        return roleService.queryRoleByApplicationId(applicationId);
    }
}
