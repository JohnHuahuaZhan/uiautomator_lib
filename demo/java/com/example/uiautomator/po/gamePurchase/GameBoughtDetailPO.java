package com.example.uiautomator.po.gamePurchase;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;

import com.example.uiautomator.po.CommonPo;

/*
    消费单详情页面
 */
public class GameBoughtDetailPO extends CommonPo {
    public GameBoughtDetailPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    private final BySelector titleSelector = By.text("消费单详情");//页面标题

    public void findTitleButton() {
        mustFindObject(titleSelector, find_timeout, "未找到'消费单详情'标题");
    }
}
