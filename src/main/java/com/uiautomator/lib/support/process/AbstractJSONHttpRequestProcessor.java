package com.uiautomator.lib.support.process;


import com.uiautomator.lib.support.network.MultiFile;
import com.uiautomator.lib.support.network.MultiText;
import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.Raw;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class AbstractJSONHttpRequestProcessor implements IHttpRequestProcessor {
    protected MyRequest parse(String requestJsonString) throws JSONException {
        JSONObject requestJson = new JSONObject(requestJsonString);
        String scheme = requestJson.getString("scheme");
        String host = requestJson.getString("host");
        String path = requestJson.getString("path");
        String port = requestJson.getString("port");
        String tag = requestJson.getString("tag");
        String requestCharset = requestJson.getString("requestCharset");


        MyRequest myRequest = new MyRequest();
        myRequest.setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .setPort(port)
                .setCharset(requestCharset)
                .setTag(tag);


        String method = requestJson.getString("method");
        String postMethod = requestJson.getString("post_method");


        //header
        Map<String, String> headers = new HashMap<>();
        myRequest.setHeaderMap(headers);
        if (requestJson.has("headers")) {
            JSONObject object = requestJson.getJSONObject("headers");
            Iterator<String> iterator =  object.keys();
            while (iterator.hasNext()){
                String key = iterator.next();
                headers.put(key, object.getString(key));
            }
        }

        //普通urlencoded 文本
        Map<String, String> data = new HashMap<>();
        myRequest.setMap(data);
        if (requestJson.has("textBody")) {
            JSONObject object = requestJson.getJSONObject("textBody");
            Iterator<String> iterator =  object.keys();
            while (iterator.hasNext()){
                String key = iterator.next();
                data.put(key, object.getString(key));
            }
        }


        //form data文本
        Map<String, MultiText> formTextData = new HashMap<>();
        myRequest.setMultiMap(formTextData);
        if (requestJson.has("mimeTextBody")) {
            JSONArray multiTextArray = requestJson.getJSONArray("mimeTextBody");
            for (int i = 0; i < multiTextArray.length(); i++) {
                JSONObject textObject = multiTextArray.getJSONObject(i);
                String key = textObject.getString("key");
                String mime = textObject.getString("mime");
                String value = textObject.getString("value");
                formTextData.put(key, new MultiText(value, mime));
            }
        }

        //form data file
        Map<String, MultiFile> formFileData = new HashMap<>();
        myRequest.setFileMap(formFileData);
        if (requestJson.has("mimeFileBody")) {
            JSONArray multiFileArray = requestJson.getJSONArray("mimeFileBody");
            for (int i = 0; i < multiFileArray.length(); i++) {
                JSONObject fileObject = multiFileArray.getJSONObject(i);
                String key = fileObject.getString("key");
                String mime = fileObject.getString("mime");
                String content = fileObject.getString("content");
                String fileName = fileObject.getString("fileName");
                byte[] bytes = this.content(content);
                formFileData.put(key, new MultiFile(fileName, bytes, mime));
            }
        }
        //raw data file
        Raw raw = new Raw();
        myRequest.setRaw(raw);
        if (requestJson.has("raw")) {
            JSONObject rawJson = requestJson.getJSONObject("raw");
            String mime = rawJson.getString("mime");
            String content = rawJson.getString("content");
            byte[] bytes = this.content(content);
            raw.setMime(mime);
            raw.setValue(bytes);
        }
        switch (method) {
            case "get": {
                myRequest.setMethod(MyRequest.Method.GET);
                break;
            }
            case "post": {
                myRequest.setMethod(MyRequest.Method.POST);
                switch (postMethod.toLowerCase()) {
                    case "url_encoded": {
                        myRequest.setPostMethod(MyRequest.PostMethod.URL_ENCODED);
                        break;
                    }
                    case "form_data": {
                        myRequest.setPostMethod(MyRequest.PostMethod.FORM_DATA);
                        break;
                    }
                    case "raw": {
                        myRequest.setPostMethod(MyRequest.PostMethod.RAW);
                        break;
                    }
                    default:
                        throw new RuntimeException("post method not supported :" + postMethod);
                }
                break;
            }
            default:
                throw new RuntimeException("request method not supported :" + method);
        }
        return myRequest;
    }


    protected abstract byte[] content(String pattern);
}
