package com.example.uiautomator.po.basic;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;
/*
* 注册手机验证页
*/
public class RegisterVerifyActivityPO extends CommonPo {

    public RegisterVerifyActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector registerMobileSelector = By.res(packageName,"edt_register_mobile");   //手机号输入框
    protected BySelector registerVerifySelector =By.res(packageName,"edt_register_verify");    //验证码输入框
    protected BySelector registerVerifyBtnSelector = By.res(packageName,"btn_register_verify");           //获取验证码按钮
    protected BySelector registerCbSelector = By.res(packageName,"cb_register");      //勾选用户注册协议框
    protected BySelector backIvSelector = By.res(packageName,"backIv");      //返回上一级图标
    protected BySelector titleTvSelector = By.res(packageName,"titleTv");      //标题
    protected BySelector btnRegisterSelector = By.res(packageName,"btn_register");      //下一步按钮

    public void inputCell(String cell){
        UiObject2 cellObject = mustFindObject(registerMobileSelector, find_timeout,"注册手机验证页找不到'手机号'输入框");
        cellObject.setText(cell);
    }

    public void inputVerity(String verifyCode){
        UiObject2 cellObject = mustFindObject(registerVerifySelector, find_timeout,"注册手机验证页找不到'验证码'输入框");
        cellObject.setText(verifyCode);
    }

    public void clickGetVerityButton(){
        UiObject2 cellObject = mustFindObject(registerVerifyBtnSelector, find_timeout,"注册手机验证页找不到'获取验证码'按钮");
        cellObject.click();
    }

    public void clickRegisterAgreement(){
        UiObject2 cellObject = mustFindObject(registerCbSelector, find_timeout,"注册手机验证页找不到'勾选用户注册协议'框");
        cellObject.click();
    }

    public void clickBackButton(){
        UiObject2 cellObject = mustFindObject(backIvSelector, find_timeout,"注册手机验证页找不到'返回上一级图标'框");
        cellObject.click();
    }

    public void clickTitleTv(){
        UiObject2 cellObject = mustFindObject(titleTvSelector, find_timeout,"注册手机验证页找不到'标题'");
        cellObject.click();
    }

    public void clickRegisterButton(){
        UiObject2 cellObject = mustFindObject(btnRegisterSelector, find_timeout,"注册手机验证页找不到'下一步'按钮");
        cellObject.click();
    }




}
