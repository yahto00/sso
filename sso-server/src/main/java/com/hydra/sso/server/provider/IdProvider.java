package com.hydra.sso.server.provider;

import java.util.UUID;

/**
 * id生成工具
 *
 * @author yahto
 * 01/01/2018 3:55 PM
 */
public class IdProvider {
    /**
     * 通过uuid生成唯一的记录id
     *
     * @return 生成的id
     */
    public static String createUUIDId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
