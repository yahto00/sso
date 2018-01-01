package com.hydra.sso.client.model;

/**
 * SSO单点登录系统返回码
 *
 * @author yahto
 * 23/12/2017 2:17 PM
 */
public class SsoResultCode extends ResultCode {
    // SSO 用户授权出错
    public final static int SSO_TOKEN_ERROR = 1001; // TOKEN未授权或已过期
    public final static int SSO_PERMISSION_ERROR = 1002; // 没有访问权限
}
