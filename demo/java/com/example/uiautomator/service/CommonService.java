package com.example.uiautomator.service;




import android.util.Base64;

import com.example.uiautomator.chshop.R;
import com.uiautomator.lib.support.exception.UIAutomatorTestException;
import com.uiautomator.lib.support.network.HttpRequestHelper;
import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.MyResponse;
import com.uiautomator.lib.support.network.util.HttpRequestUtil;
import com.uiautomator.lib.support.service.BaseService;
import com.uiautomator.lib.support.util.common.UrlUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonService extends BaseService {

    public String packageName = getContext().getStringResources(R.string.packageName);
    public String mainActivityName = getContext().getStringResources(R.string.mainActivityName);
    public long pollingEvery = Long.valueOf(getContext().getStringResources(R.string.pollingEvery));
    public long find_timeout = Long.valueOf(getContext().getStringResources(R.string.match_timeout));


    public String scheme = getContext().getStringResources(R.string.scheme);
    public String host = getContext().getStringResources(R.string.host);
    public String path = getContext().getStringResources(R.string.path);
    public String port = getContext().getStringResources(R.string.port);
    public String charset = getContext().getStringResources(R.string.charset);
    public String response_charset = getContext().getStringResources(R.string.response_charset);

    public String loginJson = getContext().getStringResources(R.string.loginJson);


    public CommonService() {
    }

    public MyResponse requestDelegate(MyRequest request) {
        try {
            delegate(request);
            return HttpRequestUtil.requestResWithoutR(request);
        } catch (IOException e) {
            throw  new  UIAutomatorTestException(e.getMessage(), e);
        }
    }
    public MyRequest delegate(MyRequest request) {
        String timestamp = new Date().getTime() + "";
        Map<String, String> param = HttpRequestHelper.getTextParams(request);
        HttpRequestHelper.updateForm(request, "timestamp", timestamp);
        HttpRequestHelper.addHeader(request, "appName", "ch_user_android");
        HttpRequestHelper.addHeader(request, "appCode", "baoying");
        HttpRequestHelper.addHeader(request, "appVersion", "1.2.0");
        if (request.getExtra().containsKey("signKey")) {
            try {
                param.put("timestamp", timestamp);
                MyRequest signRequest = HttpRequestUtil.buildPostRequest("http", "192.168.16.2", "/tool/sign/getXjqMd5SignServlet", "8080", "utf-8", param);
                byte[] result = HttpRequestUtil.request(signRequest);
                String sign = new String(result, StandardCharsets.UTF_8);
                HttpRequestHelper.updateForm(request, "sign", sign);

            } catch (Throwable throwable) {
                throw new RuntimeException(throwable.getMessage(), throwable);
            }
        }
        return request;
    }

    public String parseWithConfig(String source,Map<String, String> data, boolean base64Encoded) throws Exception {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.putAll(data);
        paramMap.put("scheme", scheme);
        paramMap.put("host", host);
        paramMap.put("path", path);
        paramMap.put("port", port);
        paramMap.put("charset", charset);
        paramMap.put("response_charset", response_charset);
        paramMap.put("packageName", packageName);
        return parse(source, paramMap, base64Encoded);
    }
    public String parse(String source,Map<String, String> data, boolean base64Encoded) throws Exception {
        String param = UrlUtil.getUrlParamsByMap(data);
        String sourceBase64 = source;
        if (!base64Encoded)
            sourceBase64 = Base64.encodeToString(source.getBytes("utf-8"), Base64.DEFAULT);
        String paramBase64 = Base64.encodeToString(param.getBytes("utf-8"), Base64.DEFAULT);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("source", sourceBase64);
        paramMap.put("param", paramBase64);
        byte[] result =  HttpRequestUtil.post("http", "192.168.16.2", "/api/v1/parse", "8085", "utf-8", paramMap);
        return new String(result, "utf-8");
    }
    public MyResponse delegate(MyResponse response) {
        return response;
    }
}
