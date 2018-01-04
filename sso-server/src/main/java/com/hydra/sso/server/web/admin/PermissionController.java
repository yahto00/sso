package com.hydra.sso.server.web.admin;

import com.hydra.sso.client.model.Result;
import com.hydra.sso.server.model.Permission;
import com.hydra.sso.server.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by yahto on 04/01/2018
 */
@RequestMapping("/admin/permission")
@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 添加权限
     *
     * @param roleId
     * @param permissionName
     * @param permissionUrl
     * @param applicationId
     * @param permissionDescription
     * @return
     */
    @RequestMapping("/addPermission.ajax")
    @ResponseBody
    public Result addPermission(@RequestParam("roleId") Long roleId,
                                @RequestParam("permissionName") String permissionName,
                                @RequestParam("permissionUrl") String permissionUrl,
                                @RequestParam("applicationId") Long applicationId,
                                @RequestParam(value = "permissionDescription", required = false) String permissionDescription) {
        Permission permission = new Permission();
        permission.setName(permissionName);
        permission.setUrl(permissionUrl);
        permission.setDescription(permissionDescription);
        permission.setCreateTime(new Date());
        return permissionService.addPermissionByRoleId(applicationId, permission, roleId);
    }
}
