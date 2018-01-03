package com.hydra.sso.server.common.servlet;

import com.hydra.sso.client.util.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

/**
 * 初始化全局参数
 * Created by yahto on 03/01/2018
 */
public class ConfigServlet extends HttpServlet {

    private static final long serialVersionUID = -7462526216386306510L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServlet.class);

    public void init() {
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("_path", servletContext.getContextPath());
        try {
            servletContext.setAttribute("_staticPath", ConfigUtils.getProperty("static.url"));
            servletContext.setAttribute("_systemName", ConfigUtils.getProperty("system.name"));
            servletContext.setAttribute("_systemAdminName", ConfigUtils.getProperty("system.admin.name"));
            servletContext.setAttribute("_companyName", ConfigUtils.getProperty("system.company.name"));
            servletContext.setAttribute("_companyPhone", ConfigUtils.getProperty("system.company.phone"));
            servletContext.setAttribute("_copyRight", ConfigUtils.getProperty("system.copy.right"));
        } catch (Exception e) {
            LOGGER.error("系统初始化参数配置有误", e);
        }
    }
}
