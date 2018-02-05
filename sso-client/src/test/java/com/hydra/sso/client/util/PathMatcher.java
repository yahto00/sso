package com.hydra.sso.client.util;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;

/**
 * Created by yahto on 02/02/2018
 */
public class PathMatcher {
    @Test
    public void pathMatcherTest() {
        org.springframework.util.PathMatcher pathMatcher = new AntPathMatcher();
        System.out.println(pathMatcher.match("/hello/*", "/hello/sayHello"));
    }
}
