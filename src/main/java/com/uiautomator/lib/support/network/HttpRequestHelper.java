package com.uiautomator.lib.support.network;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HttpRequestHelper {
    public static void updateForm(MyRequest request, Map<String, String> map){
        request.getMap().putAll(map);
    }

    public static void updateForm(MyRequest request, String key, String value){
       request.getMap().put(key, value);
    }

    public static void deleteForm(MyRequest request, String key){
        request.getMap().remove(key);
    }

    public static void addHeader(MyRequest request, String key, String value){
        request.addHeader(key,value);
    }


    public static String getTag(MyRequest request){
        return request.getTag();
    }

    public static Map<String,String> getTextParams(MyRequest request){
        Map<String, String> mapMap=request.getMap();
        Map<String,String> multiMapResult=new HashMap<>();
        Map<String, MultiText> multiMap=request.getMultiMap();
        if (!(null == multiMap)) {
            for (Map.Entry<String, MultiText> multiTextEntry : multiMap.entrySet()) {
                multiMapResult.put(multiTextEntry.getKey(), multiTextEntry.getValue().getValue());
            }
        }
        if (!(null == mapMap)) {
            multiMapResult.putAll(mapMap);
        }
        return multiMapResult;
    }
    public static String getTextParam(MyRequest request, String key){
        return getTextParams(request).get(key);
    }
}
