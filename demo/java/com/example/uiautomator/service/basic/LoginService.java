package com.example.uiautomator.service.basic;


import com.example.uiautomator.po.basic.LoginActivityPO;
import com.example.uiautomator.po.basic.SettingGestureActivityPO;
import com.example.uiautomator.service.CommonService;
import com.uiautomator.lib.support.conditions.Condition;
import com.uiautomator.lib.support.context.TestContext;
import com.uiautomator.lib.support.context.ToastAccessibilityEventListener;
import com.uiautomator.lib.support.exception.UIAutomatorTestException;
import com.uiautomator.lib.support.log.UIAutomatorLogger;
import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.util.HttpRequestUtil;

import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginService extends CommonService {
    protected String cell;
    protected String password;


    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginService(String cell, String password) {
        this.cell = cell;
        this.password = password;

        this.loginActivityPO = new LoginActivityPO(packageName, find_timeout, pollingEvery);
        this.settingGestureActivityPO = new SettingGestureActivityPO(packageName, find_timeout, pollingEvery);
    }


    private LoginActivityPO loginActivityPO ;
    private SettingGestureActivityPO settingGestureActivityPO;

    //单独分出步骤是为了步骤能够复用
    public void doSetUp() {
        stopCleanApp(packageName);
        startApp(packageName, mainActivityName);
    }
    public void doChangeToOffline(){
        loginActivityPO.clickIconIvButton();//切换到线下
    }
    //单独的步骤需要的话也要校验
    public void doLogin(){
        loginActivityPO.inputCell(cell);
        loginActivityPO.inputPsw(password);
        loginActivityPO.clickLoginButton();
    }
    public void doGesture(){
       settingGestureActivityPO.clickSkipTV();
      settingGestureActivityPO.clickOkBtn();
    }
    /**
     * 整个步骤之后需要校验
     */
    public void login() {
        doSetUp();
        doChangeToOffline();
        doLogin();
        doGesture();
    }
}
