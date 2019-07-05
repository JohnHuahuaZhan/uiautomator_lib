package com.example.uiautomator.po.gamePurchase;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;

/*
    跟单页面
 */
public class GameFollowPO extends CommonPo {
    public GameFollowPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    private final BySelector copySelector = By.res(packageName + ":id/copyLimitSelectIv");//跟单开启按钮
    private final BySelector oddsEtSelector = By.res(packageName + ":id/oddsEt");//保障赔率
    private final BySelector paySelector = By.text("确认支付");//确认支付
    private final BySelector nextSelector = By.text("确定");//确定

    public void clickOddsCloseButton() {
        UiObject2 object = findObject(oddsEtSelector);
        UiObject2 btnObject = mustFindObject(copySelector, find_timeout, "确认页找不到'允许跟单'按钮");
        if (object.isEnabled()) {
            btnObject.click();
        }
    }

    public void clickOddsButton(String odds) {
        UiObject2 object = findObject(oddsEtSelector);
        UiObject2 btnObject = mustFindObject(copySelector, find_timeout, "确认页找不到'允许跟单'按钮");
        if (!object.isEnabled()) {
            btnObject.click();
        }
        UiObject2 oddsObject = mustFindObject(oddsEtSelector, find_timeout, "确认页找不到'允许跟单'按钮");
        oddsObject.clear();
        oddsObject.setText(odds);
    }

    public void clickPayButton() {
        UiObject2 btnObject = mustFindObject(paySelector, find_timeout, "确认页找不到'确认支付'按钮");
        btnObject.click();
        systemWait(1000);

        UiObject2 yesObject = mustFindObject(nextSelector, find_timeout, "确认页找不到'确定'按钮");
        yesObject.click();
        systemWait(1000);
    }
}
