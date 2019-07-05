package com.example.uiautomator.script.gamePurchase.footballGame;

import android.Manifest;

import androidx.test.filters.SdkSuppress;
import androidx.test.rule.GrantPermissionRule;

import com.example.uiautomator.common.core.AccountManage;
import com.example.uiautomator.script.CommonTest;
import com.example.uiautomator.service.basic.LoginService;
import com.example.uiautomator.service.gamePurchase.footballGame.FootballGameService;
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
public class FootballGameTest extends CommonTest {
    private LoginService loginService;
    private FootballGameService footballGameService;
    @Rule
    public TestWatcherRule testWatcherRule = new TestWatcherRule(getDevice());
    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);

    @Parameterized.Parameter(0)
    public String memo;
    @Parameterized.Parameter(1)
    public String shopName;
    @Parameterized.Parameter(2)
    public String gameList;
    @Parameterized.Parameter(3)
    public String setKey;
    @Parameterized.Parameter(4)
    public String methodList;
    @Parameterized.Parameter(5)
    public String times;
    @Parameterized.Parameter(6)
    public String odds;

    @Parameterized.Parameters(name = "{index}: 【{0}】(shopName:{1},gameList{2},setKey{3},methodList{4},times{5},odds{6})")
    public static Collection primeNumbers() {
        String shop = "这是一家名字很长很长很长很长很长很长很长很长很长的彩店";
        return Arrays.asList(new Object[][]{
                {"【三串三】", shop, "让胜,让平@胜,平@胜,平", "", "2串1", "1", "1.8"}
        });
    }

    @Before
    public void setUp() {
        loginService = AccountManage.getYzLoginInstance();
        footballGameService = new FootballGameService(shopName, gameList, setKey, methodList, times, odds);
    }


    @Test
    public void footballGame() {
        loginService.login();
        footballGameService.enter();
        footballGameService.chooseGame();
        footballGameService.buyGame();
    }

}
