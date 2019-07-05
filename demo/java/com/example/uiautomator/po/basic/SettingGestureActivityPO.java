package com.example.uiautomator.po.basic;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;
/*
*设置手势页
*/
public class SettingGestureActivityPO extends CommonPo {
    public SettingGestureActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }
    protected BySelector skipTvSelector = By.res(packageName,"skipTv");
    protected BySelector okBtnSelector = By.res(packageName,"okBtn");

    public void clickSkipTV(){
        UiObject2 cellObject = mustFindObject(skipTvSelector, find_timeout,"手势页找不到'跳过'文本");
        cellObject.click();
    }
    public void clickOkBtn(){
        UiObject2 cellObject = mustFindObject(okBtnSelector, find_timeout,"手势页跳过弹出框找不到'确定文本");
        cellObject.click();
    }
}
