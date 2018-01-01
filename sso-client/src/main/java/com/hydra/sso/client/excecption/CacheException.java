package com.hydra.sso.client.excecption;

import com.hydra.sso.client.model.ResultCode;

/**
 * 缓存异常
 *
 * @author yahto
 * 23/12/2017 2:20 PM
 */
public class CacheException extends ApplicationException {

    private static final long serialVersionUID = 7679596258018732512L;

    public static final String MESSAGE = "缓存异常";

    public CacheException() {
        super(MESSAGE);
    }

    public CacheException(String message) {
        super(message);
        this.code = ResultCode.CACHE_ERROR;
    }

    public CacheException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResultCode.CACHE_ERROR;
    }

    public CacheException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CacheException(Throwable cause) {
        super(cause);
        this.code = ResultCode.CACHE_ERROR;
    }
}
