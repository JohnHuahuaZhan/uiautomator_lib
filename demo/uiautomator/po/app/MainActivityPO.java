package com.example.uiautomator.po.app;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiWatcher;

import com.example.uiautomator.po.CommonPo;
import com.uiautomator.lib.support.context.WatcherManager;
import com.uiautomator.lib.support.exception.UIAutomatorTestException;

public class MainActivityPO extends CommonPo {
    public static final String guide_trigger = MainActivityPO.class.getName() + "guide_trigger";
    public MainActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }
    //selectors
    protected BySelector userLogoSelector = By.res(packageName,"new_user_logo_iv");



    //watchers
    protected BySelector guideBtnSelector = By.res(packageName,"guide_btn");


    //action
    public void clickUserLogo(){
        UiObject2 btnObject = commonTrigger(userLogoSelector);
        removeCommonTrigger();
        if(null == btnObject)
                throw  new UIAutomatorTestException("首页找不到左上角'用户头像'");
        btnObject.click();
    }



    protected UiObject2 commonTrigger(BySelector selector){
        WatcherManager.getInstance().resetWatcherTriggers();
        WatcherManager.getInstance().registerWatcher(guide_trigger, new GuideWatcher(guideBtnSelector, getDevice()));
        UiObject2 object = findObject(selector, find_timeout, ()->{
            WatcherManager.getInstance().runWatchers();
        }, ()->{
            return findObject(userLogoSelector) == null;
        });
        return object;
    }
    protected void removeCommonTrigger(){
        WatcherManager.getInstance().removeWatcher(guide_trigger);
    }


    public static final class GuideWatcher implements UiWatcher{
        BySelector guideSelector;
        UiDevice uiDevice;
        public GuideWatcher(BySelector guideSelector, UiDevice uiDevice) {
            this.guideSelector = guideSelector;
            this.uiDevice = uiDevice;
        }

        @Override
        public boolean checkForCondition() {
            UiObject2 object2 = uiDevice.findObject(guideSelector);
            if(object2 != null){
                object2.click();
                return true;
            }
            return false;
        }
    }
}
