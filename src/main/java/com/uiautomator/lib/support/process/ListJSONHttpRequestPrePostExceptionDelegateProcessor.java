package com.uiautomator.lib.support.process;


import com.uiautomator.lib.support.network.HttpRequestHelper;
import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.MyResponse;
import com.uiautomator.lib.support.util.common.RegexMatcher;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ListJSONHttpRequestPrePostExceptionDelegateProcessor extends AbstractJSONHttpRequestProcessor implements IDeliverableHttpRequestProcessor{
    public static final String AUTO_PUT_REGEX  = "(\\w+|@)\\.(\\w+)";
    protected String requestJsonString;
    private boolean needAutoPut;
    protected Map<String, String> deliverMap = new HashMap<>();
    protected IHttpPrePostExceptionCallback rulePrePostCallback;
    protected IRequestDelegate requestDelegate;
    protected IByteDataSource byteDataSource;
    public ListJSONHttpRequestPrePostExceptionDelegateProcessor(String requestJsonString, IHttpPrePostExceptionCallback rulePrePostCallback, IRequestDelegate requestDelegate) {
        this( requestJsonString, rulePrePostCallback, requestDelegate, false);
    }

    public ListJSONHttpRequestPrePostExceptionDelegateProcessor(String requestJsonString, IHttpPrePostExceptionCallback rulePrePostCallback, IRequestDelegate requestDelegate, boolean needAutoPut) {
        this.requestJsonString = requestJsonString;
        this.rulePrePostCallback = rulePrePostCallback;
        this.requestDelegate = requestDelegate;
    }

    //设置数据源，用于解析你的文件上传的时候传来的路径，并返回字节数组
    public void setByteDataSource(IByteDataSource byteDataSource) {
        this.byteDataSource = byteDataSource;
    }

    public void addDeliver(String key, String value){
        this.deliverMap.put(key, value);
    }
    public void addDeliver(Map<String, String> deliver){
        this.deliverMap.putAll(deliver);
    }
    public boolean isNeedAutoPut() {
        return needAutoPut;
    }

    public void setNeedAutoPut(boolean needAutoPut) {
        this.needAutoPut = needAutoPut;
    }


    @Override
    public void start() {
        try {
            MyResponse response = null;
            JSONObject jsonObject = new JSONObject(requestJsonString);
            JSONArray array = jsonObject.getJSONArray("requests");
            for (int i = 0; i < array.length(); i++) {
                JSONObject requestJson = array.getJSONObject(i);
                String jsonAfterParse = rulePrePostCallback.preParse(requestJson.toString());
                MyRequest request = super.parse(jsonAfterParse);

                if(needAutoPut){
                    replace(request, deliverMap);
                }


                rulePrePostCallback.pre(request, response,deliverMap);

                try {
                    response = requestDelegate.delegate(request);
                    rulePrePostCallback.post(request,response,deliverMap);
                } catch (Exception e) {
                    rulePrePostCallback.exception(request,e,deliverMap);
                }
            }
        }catch (Exception e){

        }

    }

    private void replace(MyRequest request, Map<String, String> deliverMap){
        RegexMatcher matcher = new RegexMatcher();
        matcher.setRegex(AUTO_PUT_REGEX);
        String tag = HttpRequestHelper.getTag(request);

        for (Map.Entry<String, String> entry : deliverMap.entrySet()) {
            matcher.setStr(entry.getKey());
            if(matcher.match()){
                String[] values= matcher.getGroup(1);
                String key = values[1];
                if(values[0].equals("@") || values[0].equals(tag)){
                    HttpRequestHelper.updateForm(request, key, entry.getValue());
                }
            }
        }
    }

    @Override
    protected byte[] content(String pattern) {
        if(byteDataSource != null){
            return byteDataSource.getData();
        }
        return null;
    }
}
