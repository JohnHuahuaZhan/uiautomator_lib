package com.example.uiautomator.service.personalCenter.setting;

import com.example.uiautomator.common.core.AccountManage;
import com.example.uiautomator.po.myshop.MyShopPO;
import com.example.uiautomator.po.personalCenter.personsalCenterPage.PersonalCenterPO;
import com.example.uiautomator.po.personalCenter.setting.SetttingActivityPO;
import com.example.uiautomator.po.personalCenter.setting.loginPasswordManage.ChangeLoginPasswordActivityPO;
import com.example.uiautomator.po.personalCenter.setting.loginPasswordManage.LoginPasswordManageActivityPO;
import com.example.uiautomator.service.CommonService;
import com.example.uiautomator.service.basic.LoginService;

public class ChangeLoginPwdService extends CommonService {

    LoginService loginService = AccountManage.getLuoLoginInstance();
    protected String oldPassword;  //旧密码
    protected String newPassword;  //新密码
    protected String reInputPassword;  //第二次新密码
    private MyShopPO myShopPO;
    private PersonalCenterPO personalCenterPO;
    private SetttingActivityPO setttingActivityPO;
    private LoginPasswordManageActivityPO loginPasswordManageActivityPO;
    private ChangeLoginPasswordActivityPO changeLoginPasswordActivityPO;

    public static final String SUCCESS_CHANGED = "SUCCESS_CHANGED";
    public static final String FAILED_WITH_ERROR_OLDPWD = "FAILED_WITH_ERROR_OLDPWD";

    public ChangeLoginPwdService(String tag, String memo, String oldPassword, String newPassword, String reInputPassword) {
        super(tag, memo);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.reInputPassword = reInputPassword;

        this.myShopPO = new MyShopPO(packageName, find_timeout, pollingEvery);
        this.personalCenterPO = new PersonalCenterPO(packageName, find_timeout, pollingEvery);
        this.setttingActivityPO = new SetttingActivityPO(packageName, find_timeout, pollingEvery);
        this.loginPasswordManageActivityPO = new LoginPasswordManageActivityPO(packageName, find_timeout, pollingEvery);
        this.changeLoginPasswordActivityPO = new ChangeLoginPasswordActivityPO(packageName, find_timeout, pollingEvery);
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReInputPassword() {
        return reInputPassword;
    }

    public void setReInputPassword(String reInputPassword) {
        this.reInputPassword = reInputPassword;
    }

    /**
     * 初始化，打开APP，登录
     */
    public void doLogin() {
        loginService.login();
    }

    /**
     * 进入修改登录密码管理页
     */
    public void doEnterLoginPwd(){
        myShopPO.clickMainPersonButton();
        personalCenterPO.clickSettingButton();
        setttingActivityPO.clickLoginPwdButton();
    }

    /**
     * 点击确定，修改登录密码
     */
    public void doChangeLoginPwd(){
        loginPasswordManageActivityPO.clickChangePswButton();
        changeLoginPasswordActivityPO.inputOldPwd(oldPassword);
        changeLoginPasswordActivityPO.inputNewPwd(newPassword);
        changeLoginPasswordActivityPO.reInputNewPwd(newPassword);
        changeLoginPasswordActivityPO.clickOkButton();
    }

    /**
     * 登录密码改回来
     */
    public void doLoginPwdChangeBack(){
        loginPasswordManageActivityPO.clickChangePswButton();
        changeLoginPasswordActivityPO.inputOldPwd(newPassword);
        changeLoginPasswordActivityPO.inputNewPwd(oldPassword);
        changeLoginPasswordActivityPO.reInputNewPwd(oldPassword);
        changeLoginPasswordActivityPO.clickOkButton();
    }

    /**
     * 修改登录密码
     */
    public void changeLoginPwd(){
        doLogin();
        doEnterLoginPwd();
        doChangeLoginPwd();
        doLoginPwdChangeBack();
    }







}
