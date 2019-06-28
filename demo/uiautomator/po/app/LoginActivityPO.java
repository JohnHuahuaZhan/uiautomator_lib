package com.example.uiautomator.po.app;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;
import com.uiautomator.lib.support.exception.UIAutomatorTestException;

public class LoginActivityPO extends CommonPo {

    public LoginActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector cellSelector = By.res(packageName,"edt_login_mobile");
    protected BySelector pswSelector =By.res(packageName,"edt_login_password");
    protected BySelector submitSelector = By.res(packageName,"btn_login");


    public void inputCell(String cell){
        UiObject2 cellObject = findObject(cellSelector, find_timeout);
        if(null == cellObject)
            throw  new UIAutomatorTestException("登录页找不到'号码'输入框");
        cellObject.setText(cell);
    }
    public void inputPsw(String psw){
        UiObject2 pswObject = findObject(pswSelector, find_timeout);
        if(null == pswObject)
            throw  new UIAutomatorTestException("登录页找不到'密码'输入框");
        pswObject.setText(psw);
    }
    public void clickLoginButton(){
        UiObject2 btnObject = findObject(submitSelector, find_timeout);
        if(null == btnObject)
            throw  new UIAutomatorTestException("登录页找不到'登录'按钮");
        btnObject.click();
    }
}
