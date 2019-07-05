package com.example.uiautomator.po.personalCenter.setting.loginPasswordManage;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;

import com.example.uiautomator.po.CommonPo;

/**
 * 找回密码
 */
public class FindLoginPasswordActivityPO extends CommonPo {
    public FindLoginPasswordActivityPO(String packageName, long find_timeout, long pollingEvery) {
        super(packageName, find_timeout, pollingEvery);
    }

    protected BySelector backIvSelector = By.res(packageName,"backIv");    //返回上一级按钮
    protected BySelector titleTvSelector = By.res(packageName,"titleTv");    //标题
    protected BySelector phoneNumSelector = By.res(packageName, "edt_input_phone_num");  //手机号输入框
    protected BySelector findPwdTxtSelector = By.res(packageName, "smsEt");  //验证码输入框
    protected BySelector reInputNewPwtEtSelector = By.res(packageName, "reInputNewPwtEt");  //再次输入新密码输入框
    protected BySelector forgetPwtTvSelector = By.res(packageName, "forgetPwtTv");  //找回密码按钮
    protected BySelector oKbtnSelector = By.res(packageName, "oKbtn");  //确定按钮




}
