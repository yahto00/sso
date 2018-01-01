package com.hydra.sso.client.excecption;

import com.hydra.sso.client.model.ResultCode;

/**
 * 验证异常
 *
 * @author yahto
 * 23/12/2017 2:26 PM
 */
public class ValidateException extends ApplicationException {

    private static final long serialVersionUID = -696691887526888785L;

    public static final String MESSAGE = "验证异常";

    public ValidateException() {
        super(MESSAGE);
    }

    public ValidateException(String message) {
        super(message);
        this.code = ResultCode.VALIDATE_ERROR;
    }

    public ValidateException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResultCode.VALIDATE_ERROR;
    }

    public ValidateException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ValidateException(Throwable cause) {
        super(cause);
        this.code = ResultCode.VALIDATE_ERROR;
    }
}
