package com.hydra.sso.client.excecption;

import com.hydra.sso.client.model.ResultCode;

/**
 * 业务逻辑异常
 *
 * @author yahto
 * 23/12/2017 2:25 PM
 */
public class ServiceException extends ApplicationException {

    private static final long serialVersionUID = 8183505721534733839L;

    public static final String MESSAGE = "业务逻辑异常";

    public ServiceException() {
        super(MESSAGE);
    }

    public ServiceException(String message) {
        super(message);
        this.code = ResultCode.SERVICE_ERROR;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResultCode.SERVICE_ERROR;
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Throwable cause) {
        super(cause);
        this.code = ResultCode.SERVICE_ERROR;
    }
}
