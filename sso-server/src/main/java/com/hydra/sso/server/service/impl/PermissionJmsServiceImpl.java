package com.hydra.sso.server.service.impl;

import com.hydra.sso.server.service.PermissionJmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by yahto on 05/01/2018
 */
@Component
public class PermissionJmsServiceImpl implements PermissionJmsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionJmsServiceImpl.class);
    @Resource
    private JmsTemplate jmsTemplate;
    @Value("${mq.permission.queue.prefix}")
    private String queuePrefix;

    @Override
    public void send(String applicationCode) {
        if (jmsTemplate != null) {
            sendJmsMessage(jmsTemplate, applicationCode);
        }
    }

    private void sendJmsMessage(JmsTemplate jmsTemplate, final String applicationCode) {
        try {
            String destinationName = queuePrefix.concat(applicationCode);
            jmsTemplate.send(destinationName, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(applicationCode);
                }
            });
            LOGGER.info("消息服务通知appCode为：{}的应用更新权限", applicationCode);
        } catch (Exception e) {
            LOGGER.error("消息服务通知appCode为：{}的应用更新权限异常", applicationCode, e);
        }
    }
}
