package com.example.uiautomator.script.basic;

/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.Manifest;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.rule.GrantPermissionRule;

import com.example.uiautomator.script.CommonTest;
import com.example.uiautomator.service.basic.LoginService;
import com.uiautomator.lib.support.conditions.Condition;
import com.uiautomator.lib.support.context.TestContext;
import com.uiautomator.lib.support.rule.TestWatcherRule;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Basic sample for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)//调试用
//@RunWith(Parameterized.class)
@SdkSuppress(minSdkVersion = 21)
public class LoginTest extends CommonTest {

    public static final String SUCCESS_SKIP = "SUCCESS_SKIP";
    public static final String FAILED_WITH_WRONG_PSW = "FAILED_WITH_WRONG_PSW";
    LoginService loginService;
    @Rule
    public TestWatcherRule testWatcherRule = new TestWatcherRule(getDevice());
    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);

    @Parameterized.Parameter(3)
    public String tag;
    @Parameterized.Parameter(1)
    public String cell;
    @Parameterized.Parameter(2)
    public String password;
    @Parameterized.Parameter(0)
    public String memo;
    @Parameterized.Parameters(name = "{index}: 【{0}】(cell:{1},password{2})")
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { "正常登录跳过","13500000666", "654321" ,SUCCESS_SKIP },
                {"密码错误", "13500000666", "123456",FAILED_WITH_WRONG_PSW},
        });
    }

    @Before
    public void setUp() {
        //loginService = new LoginService(tag, memo, cell, password);
        loginService = new LoginService("13500000666", "654321");//调试用

    }


    @Test
    public void login() {

        switch (tag){
            case SUCCESS_SKIP:
                loginService.login();
                successCheck();
                break;
            case FAILED_WITH_WRONG_PSW:

                loginService.doSetUp();
                loginService.doChangeToOffline();
                systemWait(2000);
                loginService.doLogin();
                failCellPasswordErrorCheck();
                break;
        }
    }
    protected void failCellPasswordErrorCheck(){
        boolean ok  = Condition.waitForCondition(CoreMatchers.containsString("账号/密码输入错误"),
                () -> {
                    Map<String, String> m  = TestContext.getInstance().getToastMessage();
                    if(m.get("package").equals(packageName))
                        return m.get("message");
                    else
                        return "no match";
                },
                pollingEvery, find_timeout);
        Assert.assertThat("没有帐号/密码输入错误提示",ok, CoreMatchers.equalTo(true));

    }
    protected void successCheck(){


    }
}
