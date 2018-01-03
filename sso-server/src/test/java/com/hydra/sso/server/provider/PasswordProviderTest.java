package com.hydra.sso.server.provider;

import org.junit.Test;

/**
 * Created by yahto on 03/01/2018
 */
public class PasswordProviderTest {
    @Test
    public void test(){
        System.out.println(PasswordProvider.encrypt("123"));
    }
}
