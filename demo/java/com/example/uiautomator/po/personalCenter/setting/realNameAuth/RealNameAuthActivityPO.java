package com.example.uiautomator.po.personalCenter.setting.realNameAuth;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;

/**
 * 实名认证页
 */
public class RealNameAuthActivityPO extends CommonPo {

    public RealNameAuthActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector titleTvSelector = By.res(packageName,"titleTv");    //标题
    protected BySelector nameEtSelector = By.res(packageName,"nameEt");    //姓名输入框
    protected BySelector idCardEtSelector = By.res(packageName,"idCardEt");    //证件号输入框
    protected BySelector cbRegisterSelector = By.res(packageName,"cb_register");    //服务协议勾选框
    protected BySelector nextBtnSelector = By.res(packageName,"nextBtn");    //确定按钮
    protected BySelector notToDoTvSelector = By.res(packageName,"notToDoTv");    //暂不认证按钮
    protected BySelector cancelBtnSelector = By.res(packageName,"cancelBtn");    //弹框出现暂不认证按钮
    protected BySelector okBtnSelector = By.res(packageName,"okBtn");    //弹框出现继续实名认证按钮

    public void inputName(String name){
        UiObject2 cellObject = mustFindObject(nameEtSelector, find_timeout,"实名认证页找不到'姓名'输入框");
        cellObject.setText(name);
    }

    public void inputIdCard(String idCard){
        UiObject2 cellObject = mustFindObject(idCardEtSelector, find_timeout,"实名认证页找不到'证件号'输入框");
        cellObject.setText(idCard);
    }

    public void clickCbCheck(){
        UiObject2 cellObject = mustFindObject(cbRegisterSelector, find_timeout,"实名认证页找不到'服务协议'勾选框");
        cellObject.click();
    }

    public void clickNextButton(){
        UiObject2 cellObject = mustFindObject(nextBtnSelector, find_timeout,"实名认证页找不到'确定'按钮");
        cellObject.click();
    }

    public void clickNotToDoButton(){
        UiObject2 cellObject = mustFindObject(notToDoTvSelector, find_timeout,"实名认证页找不到'暂不认证'按钮");
        cellObject.click();
    }

    public void clickCancelButton(){
        UiObject2 cellObject = mustFindObject(cancelBtnSelector, find_timeout,"实名认证弹窗找不到'暂不认证'按钮");
        cellObject.click();
    }

    public void clickContinueButton(){
        UiObject2 cellObject = mustFindObject(okBtnSelector, find_timeout,"实名认证弹窗找不到'继续实名认证'按钮");
        cellObject.click();
    }

}
