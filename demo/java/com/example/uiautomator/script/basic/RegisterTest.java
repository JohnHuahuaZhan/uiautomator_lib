package com.example.uiautomator.script.basic;

import android.Manifest;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.rule.GrantPermissionRule;

import com.example.uiautomator.common.core.RegexTool;
import com.example.uiautomator.common.dbTool.interface_test_data.CellSectionUseDb;
import com.example.uiautomator.script.CommonTest;
import com.example.uiautomator.service.basic.LoginService;
import com.example.uiautomator.service.basic.RegisterService;
import com.uiautomator.lib.support.rule.TestWatcherRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
//@RunWith(AndroidJUnit4.class)//调试用
@RunWith(Parameterized.class)
@SdkSuppress(minSdkVersion = 21)
public class RegisterTest extends CommonTest {

//    RegisterService registerService;
//    @Rule
//    public TestWatcherRule testWatcherRule = new TestWatcherRule(getDevice());
//    @Rule
//    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
//
//    @Parameterized.Parameter(0)
//    public String memo;
//
//    @Parameterized.Parameter(1)
//    public String tag;
//
//    @Parameterized.Parameter(2)
//    public String cell;    //注册手机号
//
//    @Parameterized.Parameter(3)
//    public String password;   //密码
//
//    @Parameterized.Parameter(4)
//    public String passwordVerify; //二次密码验证
//
//    @Parameterized.Parameter(5)
//    public String nickName; //昵称
//
//    @Parameterized.Parameter(6)
//    public String verifyCode;  //手机验证码
//
//    @Parameterized.Parameters(name = "{index}: 【{0}】(tag:{1} cell:{2},password:{3},passwordVerify:{4},nickName:{5},verifyCode:{6})")
//    public static Collection primeNumbers() {
//        return Arrays.asList(new Object[][] {
//                { "正常注册", LoginService.SUCCESS_SKIP,"18368097950", "123456789qwer" ,"123456789qwer", "我大897xs爱","needToReplaced" },
//        });
//    }
//    @Before
//    public void setUp() {
//        String numberJson = CellSectionUseDb.getMaxNumber();
//        cell = String.valueOf(Long.valueOf( RegexTool.getNumber(numberJson)) + 1);
//        nickName = "自动化" + cell.substring(7);
//        registerService = new RegisterService(memo,tag,cell,password,passwordVerify,nickName);
//        //registerService = new RegisterService("1","2","17899999998","123456789qwer","123456789qwer","是4多少啊");
//
//    }
//
//
//    @Test
//    public void register() {
//        registerService.register();
//
//    }

}
