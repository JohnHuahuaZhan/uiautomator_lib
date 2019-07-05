package com.example.uiautomator.script.personalCenter.setting;

import android.Manifest;

import androidx.test.filters.SdkSuppress;
import androidx.test.rule.GrantPermissionRule;

import com.example.uiautomator.common.core.RegexTool;
import com.example.uiautomator.common.dbTool.interface_test_data.CellSectionUseDb;
import com.example.uiautomator.script.CommonTest;
import com.example.uiautomator.service.basic.LoginService;
import com.example.uiautomator.service.basic.RegisterService;
import com.example.uiautomator.service.personalCenter.setting.ChangeLoginPwdService;
import com.uiautomator.lib.support.rule.TestWatcherRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
@RunWith(Parameterized.class)
@SdkSuppress(minSdkVersion = 21)
public class ChangeLoginPwdTest extends CommonTest {

    ChangeLoginPwdService changeLoginPwdService;
    @Rule
    public TestWatcherRule testWatcherRule = new TestWatcherRule(getDevice());
    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);

    @Parameterized.Parameter(0)
    public String memo;

    @Parameterized.Parameter(1)
    public String tag;

    @Parameterized.Parameter(2)
    public String oldPassword;    //旧密码

    @Parameterized.Parameter(3)
    public String newPassword;   //新密码

    @Parameterized.Parameter(4)
    public String newPasswordVerify; //二次新密码

    @Parameterized.Parameters(name = "{index}: 【{0}】(tag:{1} oldPassword:{2},newPassword:{3},newPasswordVerify:{4})")
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { "正常", LoginService.SUCCESS_SKIP,"123456789qwer", "123456qwer" ,"123456qwer"},
        });
    }
    @Before
    public void setUp() {
        changeLoginPwdService = new ChangeLoginPwdService(memo,tag,oldPassword,newPassword,newPasswordVerify);
    }


    @Test
    public void changeLoginPassword() {
        changeLoginPwdService.changeLoginPwd();

    }





}
