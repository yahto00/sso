package com.hydra.sso.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yahto on 31/01/2018
 */
@RequestMapping("/hello")
@Controller
public class HelloController {

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello() {
        System.out.println("hello...");
        return "hello";
    }
}
