package com.example.uiautomator.po.personalCenter.setting.loginPasswordManage;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;

/**
 * 登录密码管理页面
 */
public class LoginPasswordManageActivityPO extends CommonPo {

    public LoginPasswordManageActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector backIvSelector = By.res(packageName,"backIv");    //返回上一级按钮
    protected BySelector titleTvSelector = By.res(packageName,"titleTv");    //标题
    protected BySelector changePwdTxt = By.res(packageName, "change_pwd_txt");  //修改密码按钮
    protected BySelector findPwdTxt = By.res(packageName, "find_pwd_txt");  //找回密码按钮

    public void clickBackButton(){
        UiObject2 object = mustFindObject(backIvSelector, find_timeout, "登录密码管理页找不到'返回上一级'按钮");
        object.click();
    }

    public void clickChangePswButton(){
        UiObject2 object = mustFindObject(changePwdTxt, find_timeout, "登录密码管理页找不到'修改密码'按钮");
        object.click();
    }

    public void clickFindPswButton(){
        UiObject2 object = mustFindObject(findPwdTxt, find_timeout, "登录密码管理页找不到'找回密码'按钮");
        object.click();
    }


}
