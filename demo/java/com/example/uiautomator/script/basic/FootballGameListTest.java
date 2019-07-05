package com.example.uiautomator.script.basic;

import android.Manifest;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

import com.example.uiautomator.common.CheckReturnMessage;
import com.example.uiautomator.common.LoginMessage;
import com.example.uiautomator.po.basic.BranchInfoDetailPO;
import com.example.uiautomator.po.myshop.MyShopPO;
import com.example.uiautomator.po.purchase.FootballPurchasePageObject;
import com.example.uiautomator.script.CommonTest;
import com.example.uiautomator.service.basic.LoginService;
import com.uiautomator.lib.support.exception.UIAutomatorTestException;
import com.uiautomator.lib.support.log.UIAutomatorLogger;
import com.uiautomator.lib.support.network.HttpRequestHelper;
import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.MyResponse;
import com.uiautomator.lib.support.process.IHttpPrePostExceptionCallback;
import com.uiautomator.lib.support.process.ListJSONHttpRequestPrePostExceptionDelegateProcessor;
import com.uiautomator.lib.support.rule.TestWatcherRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(AndroidJUnit4.class)//调试用
//@RunWith(Parameterized.class)
public class FootballGameListTest extends CommonTest implements IHttpPrePostExceptionCallback {
    public static final String SUCCESS = "SUCCESS";
    LoginService loginService;
    @Rule
    public TestWatcherRule testWatcherRule = new TestWatcherRule(getDevice());
    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    private MyShopPO myShopPO = new MyShopPO(packageName, find_timeout, pollingEvery);
    private BranchInfoDetailPO branchInfoDetailPO = new BranchInfoDetailPO(packageName, find_timeout, pollingEvery);
    private FootballPurchasePageObject footballPurchasePageObject = new FootballPurchasePageObject(packageName, find_timeout, pollingEvery);

    @Parameterized.Parameter(4)
    public String tag;
    @Parameterized.Parameter(3)
    public String shopName;
    @Parameterized.Parameter(1)
    public String cell;
    @Parameterized.Parameter(2)
    public String password;
    @Parameterized.Parameter(0)
    public String memo;
    @Parameterized.Parameters(name = "{index}: 【{0}】(cell:{1},password{2})")
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { "正常检查竞猜足球投注列表","13500000666", "654321" ,"怡红院",SUCCESS },
        });
    }

    @Before
    public void setUp() {
        //loginService = new LoginService(cell, password);
        loginService = new LoginService("13500000666", "654321"); shopName = "怡红院"; tag = SUCCESS;cell="13500000666";password = "654321";//调试用
    }

    @Test
    public void test(){
        try {
            //loginService.login();
          //  goToPurchasePage();
           // List<FootballPurchasePageObject.GameFrameInfo> all = doCheckList();
            getInternetListJson();
            //successCheck(all, resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void successCheck(List<FootballPurchasePageObject.GameFrameInfo> all, String json){
        UIAutomatorLogger.d(all.toString());
        UIAutomatorLogger.d(json);
    }
    public void goToPurchasePage(){
        myShopPO.clickShopTvTextContain(shopName);
        branchInfoDetailPO.clickFootballGameButton();
    }
    public  List<FootballPurchasePageObject.GameFrameInfo> doCheckList(){
       footballPurchasePageObject.expandAllGames();
        List<FootballPurchasePageObject.GameFrameInfo> all = new ArrayList<>();
        while (true){
            List<FootballPurchasePageObject.GameFrameInfo> infoList = footballPurchasePageObject.fullVisibleGamesAndCollect();
            if (infoList.isEmpty())//没有赛事
                break;
            if(all.containsAll(infoList))//到头了
                break;
            for (FootballPurchasePageObject.GameFrameInfo info : infoList) {//过滤重复
                if(!all.contains(info))
                    all.add(info);
            }
            footballPurchasePageObject.scrollFullVisibleGames();
        }
        return all;
    }
    public void getInternetListJson() throws Exception {
        String json = "{\"requests\":[{{.login}},{\"scheme\":\"{{.scheme}}\",\"tag\":\"JCZQ_SELLABLE_ISSUE_QUERY\",\"host\":\"{{.host}}\",\"port\":\"{{.port}}\",\"path\":\"/client/service.json\",\"method\":\"post\",\"post_method\":\"URL_ENCODED\",\"mimeBody\":{},\"textBody\":{\"getSupport\":\"1\",\"getSpfGgSp\":\"1\",\"getRqspfGgSp\":\"1\",\"getBfGgSp\":\"1\",\"getJqsGgSp\":\"1\",\"getBqcGgSp\":\"1\",\"getSpfDgSp\":\"1\",\"getRqspfDgSp\":\"1\",\"getJqsDgSp\":\"1\",\"getBfDgSp\":\"1\",\"getBqcDgSp\":\"1\",\"hhgg\":\"1\",\"service\":\"JCZQ_SELLABLE_ISSUE_QUERY\",\"authedUserId\":\"needToBeReplaced\",\"loginKey\":\"needToBeReplaced\"},\"requestCharset\":\"utf-8\"}]}";
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("cell", cell);
        loginMap.put("password", password);
        String login = parseWithConfig(loginJson, loginMap, true);
        loginMap.put("login", login);
        json = parseWithConfig(json, loginMap, false);
        ListJSONHttpRequestPrePostExceptionDelegateProcessor ListJSONHttpRequestPrePostExceptionDelegateProcessor = new ListJSONHttpRequestPrePostExceptionDelegateProcessor(
                json,
                this,
                (MyRequest request) -> requestDelegate(request)
                 ,true);
        ListJSONHttpRequestPrePostExceptionDelegateProcessor.start();
    }


    private String resultJson;
    @Override
    public void post(MyRequest request, MyResponse response, Map<String, String> deliver) {

            try {
                CheckReturnMessage.checkBaseResponseMessage(request, response, deliver);
                if (HttpRequestHelper.getTag(request).equals("login")) {
                    LoginMessage.initLoginMessage(request, response, deliver);
                }else if (HttpRequestHelper.getTag(request).equals("JCZQ_SELLABLE_ISSUE_QUERY")) {
                    resultJson = response.string(response_charset);
                    System.out.println();
                }
            } catch (IOException e) {
                throw new UIAutomatorTestException(e.getMessage(), e);
            }
    }
}
