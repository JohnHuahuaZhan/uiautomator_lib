package com.example.uiautomator.po.basic;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;

/**
 * 注册页
 */
public class RegisterActivityPO extends CommonPo {

    public RegisterActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector backIvSelector = By.res(packageName,"backIv");    //上一级按钮
    protected BySelector titleTvSelector = By.res(packageName,"titleTv");    //标题
    protected BySelector edtRegisterPwdSelector = By.res(packageName,"edt_register_pwd");    //密码框
    protected BySelector edtRegisterPwdConfirmSelector = By.res(packageName,"edt_register_pwd_confirm");    //再次输入密码框
    protected BySelector edtRegisterNickNameSelector = By.res(packageName,"edt_register_nickName");    //昵称输入框
    protected BySelector btnRegisterSelector = By.res(packageName,"btn_register");    //确定按钮

    public void clickBackButton(){
        UiObject2 cellObject = mustFindObject(backIvSelector, find_timeout,"注册页找不到'上一级'按钮");
        cellObject.click();
    }

    public void clickTitle(){
        UiObject2 cellObject = mustFindObject(titleTvSelector, find_timeout,"注册页找不到'手机号'输入框");
        cellObject.click();
    }

    public void inputPassword(String psw){
        UiObject2 cellObject = mustFindObject(edtRegisterPwdSelector, find_timeout,"注册页找不到'密码'输入框");
        cellObject.setText(psw);
    }

    public void inputRegisterConfirmPwd(String verifyPsw){
        UiObject2 cellObject = mustFindObject(edtRegisterPwdConfirmSelector, find_timeout,"注册页找不到'再次输入新密码'输入框");
        cellObject.setText(verifyPsw);
    }

    public void inputRegisterNickName(String verifyPsw){
        UiObject2 cellObject = mustFindObject(edtRegisterNickNameSelector, find_timeout,"注册页找不到'昵称'输入框");
        cellObject.setText(verifyPsw);
    }

    public void clickRegisterButton(){
        UiObject2 cellObject = mustFindObject(btnRegisterSelector, find_timeout,"注册页找不到'确定'输入框");
        cellObject.click();
    }





}
