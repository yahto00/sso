package com.hydra.sso.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(value = "/testAjax.ajax", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> testAjax(@RequestParam("id") Long id,
                                        @RequestParam("username") String username) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("id", id);
        map.put("username", username);
        return map;
    }
}
