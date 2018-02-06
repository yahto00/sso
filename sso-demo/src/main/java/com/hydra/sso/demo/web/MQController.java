package com.hydra.sso.demo.web;

import com.hydra.sso.client.model.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yahto on 06/02/2018
 */
@RequestMapping("/mq")
@Controller
public class MQController {
    @RequestMapping(value = "/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result data() {
        return Result.createSuccessResult("kkkk", "mq success");
    }
}
