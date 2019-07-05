package com.example.uiautomator.common;


import com.uiautomator.lib.support.network.HttpRequestHelper;
import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.MyResponse;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by xifan on 2019/1/7.
 */
public class CheckReturnMessage {


    /**
     * Xiao
     *
     * @param myRequest
     * @param myResponse
     * @param deliver
     *
     */
    //过滤流程中非逻辑性错误，后面进行二次判断
    public static void checkBaseResponseMessage(MyRequest myRequest, MyResponse myResponse, Map<String, String> deliver)  {
        try {
            String responseJson = myResponse.string("utf-8");

            String response = myResponse.string("utf-8");
            JSONObject root = new JSONObject(response);
            JSONObject responseJsonObject = root.getJSONObject("response");
            boolean success = responseJsonObject.getBoolean("success");
            Assert.assertThat(String.format("接口返回错误：接口标签#%s , 接口的Service:%s , 接口返回:%s",
                    HttpRequestHelper.getTag(myRequest),
                    HttpRequestHelper.getTextParam(myRequest, "service"),
                    responseJson
            ) + "\r\n" + responseJson,success, Matchers.equalTo(true));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
