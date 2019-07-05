package com.uiautomator.lib.support.process;


import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.MyResponse;

import java.util.Map;

public interface IHttpPrePostExceptionCallback {
    default String preParse(String source) { return source; }
    default void pre(MyRequest request, MyResponse response, Map<String, String> deliver){}
    default void post(MyRequest request, MyResponse response, Map<String, String> deliver){}
    default void exception(MyRequest request, Exception e, Map<String, String> deliver){ throw new RuntimeException(e.getMessage(), e);}
}
