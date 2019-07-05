package com.example.uiautomator.po.personalCenter.personsalCenterPage;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;

/**
 * 个人中心页
 */
public class PersonalCenterPO extends CommonPo {

    public PersonalCenterPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector personImageSelector = By.res(packageName,"person_img_iv");  //个人头像
    protected BySelector personNameSelector =By.res(packageName, "person_name");  //个人昵称
    protected BySelector changeRoleSelector =By.res(packageName, "person_change_layout");  //切换角色按钮
    protected BySelector personMessageSelector =By.res(packageName, "person_message_iv");  //我的消息按钮
    protected BySelector unreadMessageSelector = By.res(packageName, "unread_message");  //未读消息
    protected BySelector loginAccountSelector = By.res(packageName, "person_login_account");  //登录清账按钮
    protected BySelector personSettingSelector = By.res(packageName, "person_setting");  //设置按钮
    protected BySelector contactCustomServiceSelector = By.res(packageName, "contact_custom_service");  //联系客服按钮
    protected BySelector mainShopSelector = By.res(packageName, "tv_main_shop");  //店铺按钮
    protected BySelector personCenterSelector = By.res(packageName, "tv_main_person");  //个人中心按钮

    public void clickChangeRoleButton(){
        UiObject2 object = mustFindObject(changeRoleSelector, find_timeout,"个人中心页找不到'切换角色'按钮");
        object.click();
    }

    public void clickPersonMessageButton(){
        UiObject2 object =mustFindObject(personMessageSelector, find_timeout, "个人中心页找不到'我的消息'按钮");
        object.click();
    }

    public void clickLoginAccountButton(){
        UiObject2 object =mustFindObject(loginAccountSelector, find_timeout, "个人中心页找不到'登录清账'按钮");
        object.click();
    }

    public void clickSettingButton(){
        UiObject2 object =mustFindObject(personSettingSelector, find_timeout, "个人中心页找不到'设置'按钮");
        object.click();
    }

    public void clickCustomServiceButton(){
        UiObject2 object =mustFindObject(contactCustomServiceSelector, find_timeout, "个人中心页找不到'联系客服'按钮");
        object.click();
    }

    public void clickShopButton(){
        UiObject2 object =mustFindObject(mainShopSelector, find_timeout, "个人中心页找不到'店铺'按钮");
        object.click();
    }

    public void clickPersonCenterButton(){
        UiObject2 object =mustFindObject(personCenterSelector, find_timeout, "个人中心页找不到'个人中心'按钮");
        object.click();
    }

}
