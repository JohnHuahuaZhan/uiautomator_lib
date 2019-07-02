package com.uiautomator.lib.support.network;


import com.uiautomator.lib.support.util.common.network.RxUtils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyHttpClient {
    private static long  connectTimeout = 8;
    private static long  writeTimeout = 8;
    private static long  readTimeout = 8;

    private OkHttpClient client;

    private MyHttpClient() {
         this.client = new OkHttpClient.Builder()
            .sslSocketFactory(RxUtils.createSSLSocketFactory())
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout,TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .cookieJar(new MyCookieJar())
            .hostnameVerifier(new RxUtils.TrustAllHostnameVerifier())
            .build();
    }
    static MyHttpClient instance = new MyHttpClient();
    public static MyHttpClient getInstance(){
        return instance;
    }
    private  Map<String, String> commonHeader = new HashMap<>();
    public  void setCommonHeader(Map<String, String> commonHeader) {
        this.commonHeader = commonHeader;
    }
    public  void addCommonHeader(String key, String value) {
        this.commonHeader.put(key, value);
    }
    /**
     * 你需要在外调用request.body.bytes() 或者request.body.string()或者request.body.close()来回收连接
     * @param request
     * @return
     * @throws IOException
     */
    public  MyResponse request(MyRequest request) throws IOException {
        request.setCommonHeaderMap(this.commonHeader);
        Call call = this.client.newCall(request.buildRequest());
        Response response = call.execute();
        return new MyResponse(response);
    }
}
