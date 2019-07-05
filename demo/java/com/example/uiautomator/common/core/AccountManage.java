package com.example.uiautomator.common.core;

import com.example.uiautomator.service.basic.LoginService;

/**
 * 登录账号全局管理
 */
public class AccountManage {

    private AccountManage() {}

    //登录账户1
    public static LoginService getLuoLoginInstance() {
        return new LoginService(LoginService.SUCCESS_SKIP,"正常登录","16666666666","123456789qwer");
    }

    //登录账户2
    public static LoginService getYzLoginInstance() {
        return new LoginService(LoginService.SUCCESS_SKIP,"正常登录","17857330030","aa123456");
    }
}
