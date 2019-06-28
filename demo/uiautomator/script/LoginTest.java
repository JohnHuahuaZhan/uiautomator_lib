package com.example.uiautomator.script;

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

import androidx.test.filters.SdkSuppress;
import androidx.test.rule.GrantPermissionRule;

import com.example.uiautomator.service.basic.LoginService;
import com.uiautomator.lib.support.rule.TestWatcherRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Basic sample for unbundled UiAutomator.
 */
@RunWith(Parameterized.class)
@SdkSuppress(minSdkVersion = 19)
public class LoginTest extends CommonTest {

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
                { "正常登录","17300000002", "123456","success" },
                {"密码错误", "17300000002", "1234567","fail"},
        });
    }

    @Before
    public void setUp() {
        loginService = new LoginService(tag, memo, cell, password);
    }


    @Test
    public void login() {
        loginService.login();
    }

}
