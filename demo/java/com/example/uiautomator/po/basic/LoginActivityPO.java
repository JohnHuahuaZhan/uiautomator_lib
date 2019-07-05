package com.example.uiautomator.po.basic;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;
import com.uiautomator.lib.support.exception.UIAutomatorTestException;
/*
* 登录页
* */
public class LoginActivityPO extends CommonPo {

    public LoginActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector cellSelector = By.res(packageName,"edt_login_mobile");    //手机号码输入框
    protected BySelector pswSelector =By.res(packageName,"edt_login_password");    //密码输入框
    protected BySelector submitSelector = By.res(packageName,"btn_login");        //登录按钮
    protected BySelector iconIvSelector = By.res(packageName,"iconIv");            //APP图标
    protected BySelector extraRightTvSelector = By.res(packageName,"extraRightTv");  //扫一扫图标
    protected BySelector cbLoginVisibleSelector = By.res(packageName,"cb_login_visible");  //隐藏密码图标
    protected BySelector cbLoginRoleSelector = By.res(packageName,"cb_login_role");  //默认角色登录勾选框
    protected BySelector cbLoginForgetSelector = By.res(packageName,"cb_login_forget");  //忘记密码按钮
    protected BySelector btnLoginRegisterSelector = By.res(packageName,"btn_login_register");  //注册按钮
    protected BySelector checkNetTvSelector = By.res(packageName,"checkNetTv");  //网络诊断按钮
    protected BySelector checkUpdateTvSelector = By.res(packageName,"checkUpdateTv");  //检查更新按钮

    public void inputCell(String cell){
        UiObject2 cellObject = mustFindObject(cellSelector, find_timeout,"登录页找不到'号码'输入框");
        cellObject.setText(cell);
    }
    public void inputPsw(String psw){
        UiObject2 pswObject = mustFindObject(pswSelector, find_timeout,"登录页找不到'密码'输入框");
        pswObject.setText(psw);
    }
    public void clickLoginButton(){
        UiObject2 btnObject = mustFindObject(submitSelector, find_timeout,"登录页找不到'登录'按钮");
        btnObject.click();
    }
    public void clickIconIvButton(){
        UiObject2 btnObject = mustFindObject(iconIvSelector, find_timeout,"登录页找不到'APP图标'");
        btnObject.click();
    }

    public void clickExtraRightTv(){
        UiObject2 btnObject = mustFindObject(extraRightTvSelector, find_timeout, "登录页找不到'扫一扫'按钮");
        btnObject.click();
    }

    public void clickCbLoginVisibleSelector(){
        UiObject2 btnObject = mustFindObject(cbLoginVisibleSelector, find_timeout, "登录页找不到'隐藏密码'按钮");
        btnObject.click();
    }

    public void clickCbLoginRoleSelector(){
        UiObject2 btnObject = mustFindObject(cbLoginRoleSelector, find_timeout, "登录页找不到'默认角色登录'勾选框");
        btnObject.click();
    }

    public void clickCbLoginForgetSelector(){
        UiObject2 btnObject = mustFindObject(cbLoginForgetSelector, find_timeout, "登录页找不到'忘记密码'按钮");
        btnObject.click();
    }

    public void clickBtnLoginRegisterSelector(){
        UiObject2 btnObject = mustFindObject(btnLoginRegisterSelector, find_timeout, "登录页找不到'注册'按钮");
        btnObject.click();
    }

    public void clickCheckNetTvSelector(){
        UiObject2 btnObject = mustFindObject(checkNetTvSelector, find_timeout, "登录页找不到'网络诊断'按钮");
        btnObject.click();
    }

    public void clickCheckUpdateTvSelector(){
        UiObject2 btnObject = mustFindObject(checkUpdateTvSelector, find_timeout, "登录页找不到'检查更新'按钮");
        btnObject.click();
    }





}
