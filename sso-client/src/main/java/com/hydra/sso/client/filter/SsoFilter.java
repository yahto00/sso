package com.hydra.sso.client.filter;

import com.hydra.sso.client.api.model.SsoUser;
import com.hydra.sso.client.excecption.ServiceException;
import com.hydra.sso.client.model.SessionUser;
import com.hydra.sso.client.model.SsoResultCode;
import com.hydra.sso.client.util.SessionUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证用户是否已经登陆
 *
 * @author yahto
 * 23/12/2017 3:21 PM
 */
public class SsoFilter extends ClientFilter {
    // sso授权回调参数token名称
    public static final String SSO_TOKEN_NAME = "__vt_param__";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServiceException {
        String token = getParameterToken(request);
        if (token == null) {
            if (getParameterToken(request) != null) {
                // 再跳转一次当前URL，以便去掉URL中token参数
                response.sendRedirect(request.getRequestURL().toString());
            } else {
                redirectLogin(request, response);
            }
        } else if (hasLogin(token)) {
            chain.doFilter(request, response);
        } else {
            redirectLogin(request, response);
        }
    }

    /**
     * 获取Session中token
     *
     * @param request
     * @return
     */
    private String getLocalToken(HttpServletRequest request) {
        SessionUser sessionUser = SessionUtils.getSessionUser(request);
        return sessionUser == null ? null : sessionUser.getToken();
    }

    /**
     * 获取服务端回传token参数且验证
     *
     * @param request
     * @return
     * @throws IOException
     */
    private String getParameterToken(HttpServletRequest request) throws IOException {
        String token = request.getParameter(SSO_TOKEN_NAME);
        if (token != null) {
            SsoUser ssoUser = authorizationService.getAuthInfo(token);
            if (ssoUser != null) {
                invokeAuthenticationInfoInSession(request, token, ssoUser.getAccount());
                return token;
            }
        }
        return null;
    }

    /**
     * 跳转登录
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isAjaxRequest(request)) {
            throw new ServiceException(SsoResultCode.SSO_TOKEN_ERROR, "未登录或已超时");
        } else {
            SessionUtils.invalidate(request);
            String ssoLoginUrl = new StringBuilder().append(ssoServerUrl).append("/login?backUrl=")
                    .append(request.getRequestURL()).append("&appCode=").append(applicationCode).toString();

            response.sendRedirect(ssoLoginUrl);
        }
    }

    /**
     * 保存认证信息到Session
     *
     * @param request
     * @param token
     * @param account
     */
    private void invokeAuthenticationInfoInSession(HttpServletRequest request, String token, String account) {
        SessionUtils.setSessionUser(request, new SessionUser(token, account));
    }

    /**
     * 是否已登录
     *
     * @param token
     * @return
     */
    private boolean hasLogin(String token) {
        return authorizationService.validate(token);
    }

    /**
     * 是否Ajax请求
     *
     * @param request
     * @return
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }
}
