package com.example.uiautomator.po.myshop;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;

/**
 * 我的店铺页面
 */
public class MyShopPO extends CommonPo {

    public MyShopPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector tvMainShopSelector = By.res(packageName,"tv_main_shop");    //店铺按钮
    protected BySelector mainPersonSelector = By.res(packageName, "llyt_main_person");  //个人中心按钮
    protected BySelector myShopNameSelector = By.res(packageName, "my_shop_name_tv");

    public void clickMainShopButton(){
        UiObject2 object = mustFindObject(tvMainShopSelector, find_timeout,"我的店铺页找不到'店铺'按钮");
        object.click();
    }

    public void clickMainPersonButton(){
        UiObject2 object = mustFindObject(mainPersonSelector, find_timeout, "我的店铺页找不到'个人中心'按钮");
        object.click();
    }


    public void clickShopTvTextContain(String name){
        UiObject2 object = mustFindObject(myShopNameSelector.textContains(name), find_timeout, "我的店铺页找不到 指定店铺");
        object.click();
    }


}
