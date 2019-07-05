package com.example.uiautomator.po.personalCenter.setting.loginPasswordManage;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;

/**
 * 修改登录密码
 */
public class ChangeLoginPasswordActivityPO extends CommonPo {
    public ChangeLoginPasswordActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector backIvSelector = By.res(packageName,"backIv");    //返回上一级按钮
    protected BySelector titleTvSelector = By.res(packageName,"titleTv");    //标题
    protected BySelector oldPwdEtSelector = By.res(packageName, "oldPwdEt");  //旧密码输入框
    protected BySelector findPwdTxtSelector = By.res(packageName, "newPwdEt");  //新密码输入框
    protected BySelector reInputNewPwtEtSelector = By.res(packageName, "reInputNewPwtEt");  //再次输入新密码输入框
    protected BySelector forgetPwtTvSelector = By.res(packageName, "forgetPwtTv");  //找回密码按钮
    protected BySelector oKbtnSelector = By.res(packageName, "oKbtn");  //确定按钮

    public void clickBackButton(){
        UiObject2 object = mustFindObject(backIvSelector, find_timeout, "修改登陆密码页找不到'返回上一级'按钮");
        object.click();
    }

    public void inputOldPwd(String oldPassword){
        UiObject2 object = mustFindObject(oldPwdEtSelector, find_timeout, "修改登陆密码页找不到'旧密码'输入框");
        object.setText(oldPassword);
    }

    public void inputNewPwd(String newPassword){
        UiObject2 object = mustFindObject(findPwdTxtSelector, find_timeout, "修改登陆密码页找不到'新密码'输入框");
        object.setText(newPassword);
    }

    public void reInputNewPwd(String reNewPassword){
        UiObject2 object = mustFindObject(reInputNewPwtEtSelector, find_timeout, "修改登录密码页找不到'再次输入新密码'输入框");
        object.setText(reNewPassword);
    }

    public void clickForgetPassword(){
        UiObject2 object = mustFindObject(forgetPwtTvSelector, find_timeout, "修改登录密码页找不到'忘记密码'按钮");
        object.click();
    }

    public void clickOkButton(){
        UiObject2 object = mustFindObject(oKbtnSelector, find_timeout, "修改登录密码页找不到'确定'按钮");
        object.click();
    }





}
