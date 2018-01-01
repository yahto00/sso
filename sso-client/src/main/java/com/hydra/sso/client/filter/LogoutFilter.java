package com.hydra.sso.client.filter;

import com.hydra.sso.client.excecption.ServiceException;
import com.hydra.sso.client.util.SessionUtils;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 单点登录退出Filter
 *
 * @author yahto
 * 23/12/2017 4:55 PM
 */
public class LogoutFilter extends ClientFilter {
    // 单点退出成功后跳转页(配置当前应用上下文相对路径，不设置或为空表示项目根目录)
    protected String ssoBackUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        ssoBackUrl = filterConfig.getInitParameter("ssoBackUrl");
        if (ssoBackUrl == null)
            ssoBackUrl = "";
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServiceException {
        SessionUtils.invalidate(request);
        String ssoLogoutUrl = new StringBuilder().append(ssoServerUrl).append("/logout?backUrl=")
                .append(getLocalUrl(request)).append(ssoBackUrl).toString();
        response.sendRedirect(ssoLogoutUrl);
    }

    /**
     * 获取当前上下文路径
     *
     * @param request
     * @return
     */
    private String getLocalUrl(HttpServletRequest request) {
        return new StringBuilder().append(request.getScheme()).append("://").append(request.getServerName())
                .append(":").append(request.getServerPort() == 80 ? "" : request.getServerPort())
                .append(request.getContextPath()).toString();
    }
}
