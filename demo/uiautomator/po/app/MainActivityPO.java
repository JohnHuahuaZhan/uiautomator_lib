package com.example.uiautomator.po.app;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;
import com.uiautomator.lib.support.context.WatcherManager;
import com.uiautomator.lib.support.exception.UIAutomatorTestException;

public class MainActivityPO extends CommonPo {

    public MainActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }
    //selectors
    protected BySelector userLogoSelector = By.res(packageName,"new_user_logo_iv");

    protected BySelector recycleListSelector = By.res(packageName, "recycleList");

    //watchers
    protected BySelector guideBtnSelector = By.res(packageName,"guide_btn");


    //action
    public void clickUserLogo(){
        UiObject2 btnObject = findObject(userLogoSelector, find_timeout);
        if(null == btnObject)
            throw  new UIAutomatorTestException("首页找不到左上角'用户头像'");
        btnObject.click();
    }
    public void clickGuideBtn(){
        WatcherManager.getInstance().resetWatcherTriggers();
        UiObject2 btnObject = findObject(guideBtnSelector, find_timeout, ()->{
            WatcherManager.getInstance().runWatchers();
            UiObject2 sc = findObject(recycleListSelector);
            if(sc != null)
                sc.fling(Direction.UP);//刷新使得“我知道了”弹出【如果没弹出的话】
        });
        if(null == btnObject)
            throw  new UIAutomatorTestException("首页找不到欢迎页'我知道了'");
        btnObject.click();
    }
}
