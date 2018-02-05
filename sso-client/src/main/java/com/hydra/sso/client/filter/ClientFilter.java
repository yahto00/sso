package com.hydra.sso.client.filter;

import com.alibaba.fastjson.JSON;
import com.hydra.sso.client.api.service.AuthorizationService;
import com.hydra.sso.client.excecption.ServiceException;
import com.hydra.sso.client.model.Result;
import com.hydra.sso.client.util.ConfigUtils;
import com.hydra.sso.client.util.SpringUtils;
import com.hydra.sso.client.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * 初始化Filter
 *
 * @author yahto
 * 23/12/2017 3:07 PM
 */
public abstract class ClientFilter implements Filter {
    /**
     * 单点登陆服务器地址
     */
    protected String ssoServerUrl;
    /**
     * 应用码
     */
    protected String applicationCode;
    /**
     * 授权服务
     */
    protected AuthorizationService authorizationService;
    /**
     * 不需要权限验证的路径集合
     */
    protected List<String> excludeList = null;
    /**
     * 路径匹配规则
     */
    private PathMatcher pathMatcher = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        if (StringUtils.isBlank(ssoServerUrl = ConfigUtils.getProperty("sso.server.url"))) {
            throw new IllegalArgumentException("ssoServerUrl不能为空");
        }
        if (StringUtils.isBlank(applicationCode = ConfigUtils.getProperty("sso.application.code"))) {
            throw new IllegalArgumentException("ssoApplicationCode不能为空");
        }
        if ((authorizationService = SpringUtils.getBean(AuthorizationService.class)) == null) {
            throw new IllegalArgumentException("authorizationRpcService注入失败");
        }
        String excludes = filterConfig.getInitParameter("excludes");
        if (StringUtils.isNotBlank(excludes)) {
            excludeList = Arrays.asList(excludes.split(","));
            pathMatcher = new AntPathMatcher();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (matchExcludePath(request.getServletPath())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            try {
                doFilter(request, response, filterChain);
            } catch (ServiceException e) {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(HttpStatus.OK.value());
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(Result.create(e.getCode()).setMessage(e.getMessage())));
                writer.flush();
                writer.close();
            }
        }
    }

    private boolean matchExcludePath(String path) {
        if (excludeList != null) {
            for (String ignore : excludeList) {
                if (pathMatcher.match(ignore, path)) {
                    return true;
                }
            }
        }
        return false;
    }

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException, ServiceException;

    @Override
    public void destroy() {

    }
}
