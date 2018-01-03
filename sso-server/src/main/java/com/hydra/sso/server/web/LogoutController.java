package com.hydra.sso.server.web;

import com.hydra.sso.client.util.CookieUtils;
import com.hydra.sso.client.util.SessionUtils;
import com.hydra.sso.client.util.StringUtils;
import com.hydra.sso.server.common.token.TokenManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yahto on 03/01/2018
 */
@RequestMapping("/logout")
@Controller
public class LogoutController {
    @Resource
    private TokenManager tokenManager;

    @RequestMapping(method = RequestMethod.GET)
    public String logout(@RequestParam("backUrl") String backUrl,
                         HttpServletRequest request) {
        String token = CookieUtils.getCookie(request, "token");
        if (StringUtils.isNotBlank(token)) {
            tokenManager.remove(token);
        }
        SessionUtils.invalidate(request);
        return "redirect:" + (StringUtils.isBlank(backUrl) ? "/admin/admin" : backUrl);
    }
}
