package com.example.uiautomator.kksport.service.basic;

import com.example.uiautomator.kksport.po.app.LoginActivityPO;
import com.example.uiautomator.kksport.po.app.MainActivityPO;
import com.example.uiautomator.kksport.po.app.SplashActivityPO;
import com.example.uiautomator.kksport.service.CommonService;

public class LoginService extends CommonService {
    protected String cell;
    protected String password;

    public LoginService(String tag, String memo, String cell, String password) {
        super(tag, memo);
        this.cell = cell;
        this.password = password;

        this.loginActivityPO = new LoginActivityPO(packageName, find_timeout, pollingEvery);
        this.splashActivityPO = new SplashActivityPO(packageName, find_timeout, pollingEvery);
        this.mainActivityPO = new MainActivityPO(packageName, find_timeout, pollingEvery);
    }


    private LoginActivityPO loginActivityPO ;
    private SplashActivityPO splashActivityPO ;
    private MainActivityPO mainActivityPO ;

    //单独分出步骤是为了步骤能够复用
    public void doSetUp() {
        stopCleanApp(packageName);
        startApp(packageName, mainActivityName);
    }
    public void doSwap() {
        splashActivityPO.pass();
        splashActivityPO.pass();
        splashActivityPO.clickStartBtn();
    }
    public void doGoToLogin() {
        mainActivityPO.clickGuideBtn();
        mainActivityPO.clickUserLogo();
    }
    //单独的步骤无需校验
    public void doLogin(){
        loginActivityPO.inputCell(cell);
        loginActivityPO.inputPsw(password);
        loginActivityPO.clickLoginButton();

    }

    /**
     * 整个步骤之后需要校验
     */
    public void login() {
        doSetUp();
        doSwap();
        doGoToLogin();
        doLogin();

        //校验
        switch (tag){
            case "success":
                checkSuccess();
                break;
            case "failed":
                checkFailed();
        }
    }
    public void checkSuccess(){
        //做一些校验断言。断言信息附上memo
    }
    public void checkFailed(){

    }
}
