package com.example.uiautomator.po.basic;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;


/**
 * 店铺详情页面
 */
public class BranchInfoDetailPO extends CommonPo {
    public BranchInfoDetailPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector AccountBookSelector = By.res(packageName, "storeAccountLayout");
    protected BySelector backIvSelector = By.res(packageName, "backIv");
    private final BySelector gameSelector = By.res(packageName + ":id/jczqShowIv");


    public void clickAccountBookButton() {
        UiObject2 AccountBookObject = mustFindObject(AccountBookSelector, find_timeout, "店铺详情找不到店内账本元素");
        AccountBookObject.click();
    }

    public void clickComeBackButton() {
        UiObject2 uiObject2 = mustFindObject(backIvSelector, find_timeout, "店铺详情找不到左上角返回元素");
        uiObject2.click();
    }

    public void clickFootballGameButton() {
        UiObject2 footballGameObject = mustFindObject(gameSelector, find_timeout, "店铺详情找不到'竞彩足球'");
        footballGameObject.click();
    }


}
