package com.hydra.sso.server.web;

import com.hydra.sso.client.filter.SsoFilter;
import com.hydra.sso.client.model.Result;
import com.hydra.sso.client.model.ResultCode;
import com.hydra.sso.client.util.CookieUtils;
import com.hydra.sso.client.util.StringUtils;
import com.hydra.sso.server.common.token.TokenManager;
import com.hydra.sso.server.model.LoginUser;
import com.hydra.sso.server.model.User;
import com.hydra.sso.server.provider.IdProvider;
import com.hydra.sso.server.provider.PasswordProvider;
import com.hydra.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author yahto
 * 01/01/2018 3:35 PM
 */
@RequestMapping("/login")
@Controller
public class LoginController {
    public static final String LOGIN_PATH = "/login";

    @Autowired
    private UserService userService;

    @Resource
    private TokenManager tokenManager;

    /**
     * 单点系统登录提交
     *
     * @param backUrl
     * @param applicationCode
     * @param account
     * @param password
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam("backUrl") String backUrl,
                        @RequestParam("applicationCode") String applicationCode,
                        @RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response) throws UnsupportedEncodingException {
        Result result = userService.login(getIpAddress(request), account, applicationCode, PasswordProvider.encrypt(password));
        if (!result.getCode().equals(ResultCode.SUCCESS)) {
            request.setAttribute("errorMessage", result.getMessage());
            return goLoginPath(backUrl, applicationCode, request);
        }
        User user = (User) result.getData();
        LoginUser loginUser = new LoginUser(user.getId(), user.getAccount());
        String token = CookieUtils.getCookie(request, "token");
        if (StringUtils.isBlank(token) || tokenManager.validate(token) == null) {
            token = createToken(loginUser);
            addTokenInCookie(token, request, response);
        }
        backUrl = URLDecoder.decode(backUrl, "utf-8");
        return "redirect:" + authBackUrl(backUrl, token);
    }

    /**
     * 登录页
     *
     * @param backUrl
     * @param applicationCode
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String login(@RequestParam("backUrl") String backUrl,
                        @RequestParam("applicationCode") String applicationCode,
                        HttpServletRequest request) {
        String token = CookieUtils.getCookie(request, "token");
        if (token == null) {
            return goLoginPath(backUrl, applicationCode, request);
        } else {
            LoginUser loginUser = tokenManager.validate(token);
            if (loginUser != null) {
                return "redirect:" + authBackUrl(backUrl, token);
            } else {
                return goLoginPath(backUrl, applicationCode, request);
            }
        }
    }

    private String goLoginPath(String backUrl, String applicationCode, HttpServletRequest request) {
        request.setAttribute("backUrl", backUrl);
        request.setAttribute("applicationCode", applicationCode);
        return LOGIN_PATH;
    }

    private String authBackUrl(String backUrl, String token) {
        StringBuilder sbf = new StringBuilder(backUrl);
        if (backUrl.indexOf("?") > 0) {
            sbf.append("&");
        } else {
            sbf.append("?");
        }
        sbf.append(SsoFilter.SSO_TOKEN_NAME).append("=").append(token);
        return sbf.toString();
    }

    private String createToken(LoginUser loginUser) {
        // 生成token
        String token = IdProvider.createUUIDId();

        // 缓存中添加token对应User
        tokenManager.addToken(token, loginUser);
        return token;
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    protected String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    private void addTokenInCookie(String token, HttpServletRequest request, HttpServletResponse response) {
        // Cookie添加token
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        if ("https".equals(request.getScheme())) {
            cookie.setSecure(true);
        }
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

}
