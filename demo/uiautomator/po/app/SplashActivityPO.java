package com.example.uiautomator.kksport.po.app;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;

import com.example.uiautomator.kksport.po.CommonPo;
import com.uiautomator.lib.support.exception.UIAutomatorTestException;

public class SplashActivityPO extends CommonPo {

    public SplashActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
        this.page = new UiScrollable(res(packageName, "iv"));
        this.page.setAsHorizontalList();
    }
    private UiScrollable page;
    private BySelector startBtnSelector = By.res(packageName, "btn");

    public void pass(){
        try {
            this.page.flingForward();
        } catch (UiObjectNotFoundException e) {
            throw  new UIAutomatorTestException(e.getMessage(), e);
        }
    }
    public void clickStartBtn(){
        UiObject2 btnObject = findObject(startBtnSelector, find_timeout, ()->{
            pass();//找不到就多fling几次
        });
        if(null == btnObject)
            throw  new UIAutomatorTestException("向导页找不到立即体验按钮");
        btnObject.click();
    }
}
