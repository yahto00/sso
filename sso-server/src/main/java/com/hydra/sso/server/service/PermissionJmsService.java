package com.hydra.sso.server.service;

/**
 * Created by yahto on 05/01/2018
 */
public interface PermissionJmsService {
    /**
     * 发送权限变更消息
     *
     * @param applicationCode
     */
    void send(String applicationCode);
}
