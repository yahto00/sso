package com.hydra.sso.client.filter;

import com.hydra.sso.client.api.model.SsoPermission;
import com.hydra.sso.client.excecption.ServiceException;
import com.hydra.sso.client.model.SessionPermission;
import com.hydra.sso.client.model.SessionUser;
import com.hydra.sso.client.model.SsoResultCode;
import com.hydra.sso.client.monitor.PermissionJmsMonitor;
import com.hydra.sso.client.util.ApplicationPermissionUtils;
import com.hydra.sso.client.util.SessionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 验证用户是否拥有访问权限
 *
 * @author yahto
 * 23/12/2017 3:40 PM
 */
public class PermissionFilter extends ClientFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        ApplicationPermissionUtils.initApplicationPermissions(authorizationService, applicationCode);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServiceException {
        String path = request.getServletPath();
        if (isPermitted(request, path)) {
            chain.doFilter(request, response);
        } else if (!ApplicationPermissionUtils.getApplicationPermissionSet().contains(path)) {
            chain.doFilter(request, response);
        } else {
            throw new ServiceException(SsoResultCode.SSO_PERMISSION_ERROR, "没有访问权限");
        }
    }

    private boolean isPermitted(HttpServletRequest request, String path) {
        Set<String> permissionSet = getLocalPermissionSet(request);
        return permissionSet.contains(path);
    }

    private Set<String> getLocalPermissionSet(HttpServletRequest request) {
        SessionPermission sessionPermission = SessionUtils.getSessionPermission(request);
        if (sessionPermission == null || sessionPermissionChanged(request)) {
            sessionPermission = invokePermissionInSession(request);
        }
        return sessionPermission.getPermissionSet();
    }

    private boolean sessionPermissionChanged(HttpServletRequest request) {
        SessionUser user = SessionUtils.getSessionUser(request);
        return PermissionJmsMonitor.isChanged && !PermissionJmsMonitor.tokenSet.contains(user.getToken());
    }

    public SessionPermission invokePermissionInSession(HttpServletRequest request) {
        SessionUser user = SessionUtils.getSessionUser(request);
        List<SsoPermission> permissionList = authorizationService.getPermissionList(user.getToken(), applicationCode);
        Set<String> operateSet = new HashSet<String>();
        for (SsoPermission ssoPermission : permissionList) {
            if (ssoPermission.getUrl() != null) {
                operateSet.add(ssoPermission.getUrl());
            }
        }
        SessionPermission sessionPermission = new SessionPermission();
        // 保存登录用户没有权限的URL，方便前端去隐藏相应操作按钮
        Set<String> noPermissionSet = new HashSet<String>(ApplicationPermissionUtils.getApplicationPermissionSet());
        noPermissionSet.removeAll(operateSet);
        // 保存登录用户没有权限的URL
        sessionPermission.setNoPermissions(StringUtils.arrayToDelimitedString(noPermissionSet.toArray(), ","));
        // 保存登录用户权限列表
        sessionPermission.setPermissionSet(operateSet);
        SessionUtils.setSessionPermission(request, sessionPermission);
        // 添加权限监控集合，当前session已更新最新权限
        if (PermissionJmsMonitor.isChanged) {
            PermissionJmsMonitor.tokenSet.add(user.getToken());
        }
        return sessionPermission;
    }
}
