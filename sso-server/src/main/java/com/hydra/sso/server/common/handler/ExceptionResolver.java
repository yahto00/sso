package com.hydra.sso.server.common.handler;

import com.alibaba.fastjson.JSON;
import com.hydra.sso.client.excecption.ApplicationException;
import com.hydra.sso.client.model.Result;
import com.hydra.sso.client.model.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yahto
 * 23/12/2017 2:29 PM
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception exception) {
        Result result = null;
        if (exception instanceof ApplicationException) {
            ApplicationException ae = (ApplicationException) exception;
            result = Result.create(ae.getCode()).setMessage(ae.getMessage());
        } else {
            result = Result.create(ResultCode.ERROR).setMessage("未知错误");
            LOGGER.error(exception.getMessage(), exception);
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
            writer.flush();
            writer.close();
        } catch (IOException ie) {
            LOGGER.error("Failed to serialize the object to json for exception resolver!", ie);
        }
        return new ModelAndView();
    }
}
