package com.example.uiautomator.common;



import com.uiautomator.lib.support.network.HttpRequestHelper;
import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.MyResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by xifan on 2018/12/27.
 */
public class LoginMessage {
    public static void setLoginMessage(MyRequest myRequest, MyResponse myResponse, Map<String, String> deliver){
        try {
            HttpRequestHelper.updateForm(myRequest,"authedUserId", deliver.get("authedUserId"));
            HttpRequestHelper.updateForm(myRequest,"loginKey", deliver.get("loginKey"));
            HttpRequestHelper.updateForm(myRequest,"oneAuthId", deliver.get("oneAuthId"));
            HttpRequestHelper.updateForm(myRequest, "signKey", deliver.get("signKey"));


        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    public static void initLoginMessage(MyRequest myRequest, MyResponse myResponse, Map<String, String> deliver) {
        try {
            String response = myResponse.string("utf-8");
            JSONObject root = new JSONObject(response);
            JSONObject responseJsonObject = root.getJSONObject("response");
            JSONObject userPlatformLogin = responseJsonObject.getJSONObject("userPlatformLogin");
            JSONObject oneAuthPlatformLogin = responseJsonObject.getJSONObject("oneAuthPlatformLogin");
           String  authedUserId = userPlatformLogin.getString("userId");
           String  loginKey = userPlatformLogin.getString("loginKey");
           String  signKey = userPlatformLogin.getString("signKey");
           String  oneAuthId = oneAuthPlatformLogin.getString("oneAuthId");
           String  oneAuthLoginKey = oneAuthPlatformLogin.getString("loginKey");
           String  oneAuthSignKey = oneAuthPlatformLogin.getString("signKey");

            deliver.put("pfloginKey", oneAuthLoginKey);
            deliver.put("@.pfloginKey", oneAuthLoginKey);
            deliver.put("pfsignKey", oneAuthSignKey);
            deliver.put("@.pfsignKey", oneAuthSignKey);
            deliver.put("authedUserId", authedUserId);
            deliver.put("loginKey", loginKey);
            deliver.put("signKey", signKey);
            deliver.put("oneAuthId", oneAuthId);
            deliver.put("oneAuthLoginKey", oneAuthLoginKey);
            deliver.put("oneAuthSignKey", oneAuthSignKey);

            deliver.put("@.authedUserId", authedUserId);
            deliver.put("@.loginKey", loginKey);
            deliver.put("@.signKey", signKey);
            deliver.put("@.oneAuthId", oneAuthId);
            deliver.put("@.oneAuthLoginKey", oneAuthLoginKey);
            deliver.put("@.oneAuthSignKey", oneAuthSignKey);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }



    public static void getCode(MyRequest myRequest, MyResponse myResponse, Map<String, String> deliver) throws IOException {
        String BenchCaptchaVerifyCode=myResponse.header("BenchCaptchaVerifyCode");
        deliver.put("checkCode", BenchCaptchaVerifyCode);
        deliver.put("@.checkCode", BenchCaptchaVerifyCode);
    }

}
