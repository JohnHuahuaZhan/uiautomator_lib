package com.example.uiautomator.service.basic;

import android.util.Log;

import com.example.uiautomator.common.core.RegexTool;
import com.example.uiautomator.common.dbTool.interface_test_data.CellSectionUseDb;
import com.example.uiautomator.po.basic.AccountBindActivityPO;
import com.example.uiautomator.po.basic.LoginActivityPO;
import com.example.uiautomator.po.basic.RegisterActivityPO;
import com.example.uiautomator.po.basic.RegisterVerifyActivityPO;
import com.example.uiautomator.po.basic.SettingGestureActivityPO;
import com.example.uiautomator.po.personalCenter.setting.realNameAuth.RealNameAuthActivityPO;
import com.example.uiautomator.service.CommonService;
import com.uiautomator.lib.support.conditions.Condition;
import com.uiautomator.lib.support.context.TestContext;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Assert;

import java.util.Map;

public class RegisterService extends CommonService {

    protected String cell;    //注册手机号
    protected String verifyCode;     //手机验证码
    protected String password;   //密码
    protected String passwordVerify; //二次密码验证
    protected String nickName; //昵称

    private LoginActivityPO loginActivityPO ;
    private RegisterVerifyActivityPO registerVerifyActivityPO;
    private RegisterActivityPO registerActivityPO;
    private SettingGestureActivityPO settingGestureActivityPO;
    private RealNameAuthActivityPO realNameAuthActivityPO;
    private AccountBindActivityPO accountBindActivityPO;

    public static final String SUCCESS_SKIP = "SUCCESS_SKIP";
    public static final String FAILED_WITH_SAME_CELL = "FAILED_WITH_SAME_CELL";
    public static final String FAILED_WITH_ERROR_VERIFY_CODE = "FAILED_WITH_ERROR_VERIFY_CODE";

    public RegisterService(String tag, String memo, String cell, String password, String passwordVerify, String nickName) {
        super(tag, memo);
        this.cell = cell;
        this.password = password;
        this.passwordVerify = passwordVerify;
        this.nickName = nickName;

        this.loginActivityPO = new LoginActivityPO(packageName, find_timeout, pollingEvery);
        this.registerVerifyActivityPO = new RegisterVerifyActivityPO(packageName, find_timeout, pollingEvery);
        this.registerActivityPO = new RegisterActivityPO(packageName, find_timeout, pollingEvery);
        this.settingGestureActivityPO = new SettingGestureActivityPO(packageName, find_timeout, pollingEvery);
        this.realNameAuthActivityPO = new RealNameAuthActivityPO(packageName, find_timeout, pollingEvery);
        this.accountBindActivityPO = new AccountBindActivityPO(packageName, find_timeout, pollingEvery);
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * 初始化，打开APP
     */
    public void doSetUp() {
        stopCleanApp(packageName);
        startApp(packageName, mainActivityName);
    }

    /**
     * 切换到线下,点击注册进入手机验证页
     */
    public void doChangeOffLine(){
        loginActivityPO.clickIconIvButton();   //切换到线下
        loginActivityPO.clickBtnLoginRegisterSelector();   //点击注册,进入注册页
    }


    /**
     * 输入手机号、验证码，点击下一步，进入注册页
     */
    public void doRegisterVerityCode(){
        try {
            registerVerifyActivityPO.inputCell(cell);
            registerVerifyActivityPO.clickGetVerityButton();
//            sameCell();
            Thread.sleep(500);
            verifyCode = RegexTool.getCellVerifyCode(cell);   //获取验证码
            registerVerifyActivityPO.inputVerity(verifyCode);
            registerVerifyActivityPO.clickRegisterButton();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable.getMessage(), throwable);
        }
    }

    /**
     * 输入密码，第二次输入密码和昵称，点击完成
     */
    public void doRegister(){
        registerActivityPO.inputPassword(password);
        registerActivityPO.inputRegisterConfirmPwd(passwordVerify);
        registerActivityPO.inputRegisterNickName(nickName);
        registerActivityPO.clickRegisterButton();
        //注册成功后更新数据库
        try {
            CellSectionUseDb.updateNumber(3);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable.getMessage(), throwable);
        }
    }

    /**
     * 跳过设置手势
     */
    public void doGesture(){
        settingGestureActivityPO.clickSkipTV();
        settingGestureActivityPO.clickOkBtn();
    }

    /**
     * 跳过实名认证
     */
    public void doNotAuth(){
        realNameAuthActivityPO.clickNotToDoButton();
        realNameAuthActivityPO.clickCancelButton();
    }

    /**
     * 跳过KK体育绑定
     */
    public void doNotBind(){
        accountBindActivityPO.clickCancleButton();
        accountBindActivityPO.clickCancelBtn();
    }


    /**
     * 正常注册，跳过实名认证，进入首页
     */
    public void register(){
        doSetUp();
        doChangeOffLine();
        doRegisterVerityCode();
        doRegister();
        doGesture();
        doNotAuth();
        doNotBind();
    }

    /**
     * 手机号被注册
     */
    protected void sameCell(){
        boolean ok  = Condition.waitForCondition(CoreMatchers.containsString("手机号已存在"),
                () -> {
            Map<String, String> m  = TestContext.getInstance().getToastMessage();
            if(m.get("package").equals(packageName))
                return m.get("message");
            else
                return "no match";
        },
        pollingEvery, find_timeout);
        Log.d("ok", String.valueOf(ok));

        Matcher s =CoreMatchers.containsString("手机号已存在");
        //如果已被注册，则在mysql数据库插入一条数据
        if(ok){
            try {
                CellSectionUseDb.updateNumber(3);
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable.getMessage(), throwable);
            }
        }
        Assert.assertThat("手机号已存在",ok, CoreMatchers.equalTo(true));
    }




}
