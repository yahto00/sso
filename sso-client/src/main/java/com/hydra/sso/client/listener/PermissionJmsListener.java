package com.hydra.sso.client.listener;

import com.hydra.sso.client.api.service.AuthorizationService;
import com.hydra.sso.client.monitor.PermissionJmsMonitor;
import com.hydra.sso.client.util.ApplicationPermissionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author yahto
 * 23/12/2017 4:57 PM
 */
@Component
public class PermissionJmsListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionJmsListener.class);
    @Value("${sso.application.code}")
    private String applicationCode;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public void onMessage(Message message) {
        String ssoApplicationCode = null;
        try {
            ssoApplicationCode = ((TextMessage) message).getText();
        } catch (JMSException e) {
            LOGGER.error("Jms illegal message!", e);
        }
        if (applicationCode.equals(ssoApplicationCode)) {
            // 1.通知当前子系统权限有变动修改
            PermissionJmsMonitor.isChanged = true;
            // 2.清除已获取最新权限的token集合(Session级别)
            PermissionJmsMonitor.tokenSet.clear();
            // 3.更新应用权限（Application级别）
            ApplicationPermissionUtils.initApplicationPermissions(authorizationService, applicationCode);
            LOGGER.info("成功通知applicationCode为：{}的应用更新权限！", ssoApplicationCode);
        }
    }
}
