package com.example.uiautomator.po.basic;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;


/**
 * 账号绑定页
 */
public class AccountBindActivityPO extends CommonPo {
    public AccountBindActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector titleTvSelector = By.res(packageName,"titleTv");    //标题
    protected BySelector toBIndTvSelector = By.res(packageName,"toBIndTv");    //前往绑定KK体育按钮
    protected BySelector cancelTvSelector = By.res(packageName,"cancelTv");    //暂不绑定按钮
    protected BySelector aboutKkTvSelector = By.res(packageName,"aboutKkTv");    //关于KK体育文本
    protected BySelector cancelBtnSelector = By.res(packageName,"cancelBtn");    //暂不绑定弹出框
    protected BySelector okBtnSelector = By.res(packageName,"okBtn");    //继续绑定弹出框


    public void clickToBindTvButton(){
        UiObject2 object = mustFindObject(toBIndTvSelector, find_timeout,"账号绑定页找不到'前往绑定KK体育'按钮");
        object.click();
    }

    public void clickCancleButton(){
        UiObject2 object = mustFindObject(cancelTvSelector, find_timeout,"账号绑定页找不到'暂不绑定'按钮");
        object.click();
    }

    public void clickCancelBtn(){
        UiObject2 object = mustFindObject(cancelBtnSelector, find_timeout,"账号绑定弹出框找不到'暂不绑定'按钮");
        object.click();
    }

    public void clickOkBtn(){
        UiObject2 object = mustFindObject(okBtnSelector, find_timeout,"账号绑定弹出框找不到'继续绑定'按钮");
        object.click();
    }
}
