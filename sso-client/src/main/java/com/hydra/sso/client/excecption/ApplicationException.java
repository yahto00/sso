package com.hydra.sso.client.excecption;

import com.hydra.sso.client.model.ResultCode;

/**
 * 应用异常
 *
 * @author yahto
 * 23/12/2017 2:19 PM
 */
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -1052004694316990150L;

    public static final String MESSAGE = "应用异常";

    protected int code = ResultCode.APPLICATION_ERROR;

    public ApplicationException() {
        super(MESSAGE);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
