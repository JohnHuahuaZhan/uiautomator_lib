package com.example.uiautomator.po.gamePurchase;

import android.util.Log;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;

import java.util.List;

/*
    生成消费单页面
 */
public class GameMethodPO extends CommonPo {
    public GameMethodPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    private final BySelector methodSelector = By.res(packageName + ":id/passTypeTv");//展开过关方式
    private final BySelector methodListSelector = By.res(packageName + ":id/pass_simple_container");//过关方式列表
    private final BySelector numEtSelector = By.res(packageName + ":id/numEt");//倍数
    private final BySelector goOnSelector = By.text("下一步");//下一步

    public void clickKeyButton(String key) {
        UiObject2 gameShowSelector = getDevice().findObject(By.res(packageName + ":id/betLv"));
        List<UiObject2> gameList = gameShowSelector.getChildren();
        UiObject2 btnObject = mustFindObject(gameList.get(Integer.parseInt(key) - 1), By.res(packageName + ":id/tb_dan"), find_timeout, "找不到" + key + "比赛");
        btnObject.click();
    }

    public void clickMethodButton() {
        UiObject2 btnObject = mustFindObject(methodSelector, find_timeout, "生成订单页找不到串关玩法按钮");
        btnObject.click();
    }

    public void clickMethodSelectedButton() {
        UiObject2 listObject = mustFindObject(methodListSelector, find_timeout, "生成订单页找不到过关方式列表");
        List<UiObject2> methodList = listObject.findObjects(By.checked(true));
        Log.d("myInfo", "size  " + methodList.size());
        for (UiObject2 method : methodList) {
            while (method.isChecked()) {
                Log.d("myInfo", "method  " + method.getText());
                method.click();
            }
        }
    }

    public void clickMethodListButton(String method) {
        BySelector methodSelector = By.text(method);
        UiObject2 btnMethodObject = mustFindObject(methodSelector, find_timeout, "串关找不到" + method);
        btnMethodObject.click();
        systemWait(1000);
    }

    public void inputTimes(String times) {
        UiObject2 timesObject = mustFindObject(numEtSelector, find_timeout, "生成订单页找不到投注倍数框");
        timesObject.clear();
        timesObject.setText(times);
    }

    public void clickGoOnButton() {
        UiObject2 btnObject = mustFindObject(goOnSelector, find_timeout, "生成订单页找不到'下一步'按钮");
        btnObject.click();
    }
}
