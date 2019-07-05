package com.example.uiautomator.po.personalCenter.setting;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject2;

import com.example.uiautomator.po.CommonPo;

/**
 * 设置页
 */
public class SetttingActivityPO extends CommonPo {
    public SetttingActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector backIvSelector = By.res(packageName,"backIv");    //返回上一级
    protected BySelector titleTvSelector = By.res(packageName,"titleTv");    //标题
    protected BySelector loginPwdManagerSelector = By.res(packageName,"login_pwd_manager");    //密码登录管理按钮
    protected BySelector gesturePwdManagerSelector = By.res(packageName,"gesture_pwd_manager");    //手势密码管理按钮
    protected BySelector certificationManagerSelector = By.res(packageName,"certification_manager");    //实名认证按钮
    protected BySelector phoneBindSelector = By.res(packageName,"phone_bind");    //手机号绑定按钮
    protected BySelector thirdBindManagerSelector = By.res(packageName,"third_bind_manager");    //第三方绑定按钮
    protected BySelector aboutUsSelector = By.res(packageName,"about_us");    //关于我们按钮
    protected BySelector updateTvSelector = By.res(packageName,"update_tv");    //检查更新按钮
    protected BySelector logOutLaySelector = By.res(packageName, "log_out_lay");  //退出登录按钮

    public void clickBackButton(){
        UiObject2 object = mustFindObject(backIvSelector, find_timeout,"设置页找不到'姓名'按钮");
        object.click();
    }

    public void clickLoginPwdButton(){
        UiObject2 object = mustFindObject(loginPwdManagerSelector, find_timeout,"设置页找不到'登录密码管理'按钮");
        object.click();
    }

    public void clickGesturePwdButton(){
        UiObject2 object = mustFindObject(gesturePwdManagerSelector, find_timeout, "设置页找不到'手势密码管理'按钮");
        object.click();
    }

    public void clickCertificationButton(){
        UiObject2 object = mustFindObject(certificationManagerSelector, find_timeout, "设置页找不到'实名认证'按钮");
        object.click();
    }
    public void clickPhoneBindButton(){
        UiObject2 object = mustFindObject(phoneBindSelector, find_timeout, "设置页找不到'手机号绑定'按钮");
        object.click();
    }

    public void clickThirdBindButton(){
        UiObject2 object = mustFindObject(thirdBindManagerSelector, find_timeout, "设置页找不到'第三方绑定'按钮");
        object.click();
    }

    public void clickAboutUsButton(){
        UiObject2 object = mustFindObject(aboutUsSelector, find_timeout, "设置页找不到'关于我们'按钮");
        object.click();
    }

    public void clickUpdateTvButton(){
        UiObject2 object = mustFindObject(updateTvSelector, find_timeout, "设置页找不到'检查更新'按钮");
        object.click();
    }

    public void clickLayoutTvButton(){
        UiObject2 object = mustFindObject(logOutLaySelector, find_timeout, "设置页找不到'退出登录'按钮");
        object.click();
    }





}
