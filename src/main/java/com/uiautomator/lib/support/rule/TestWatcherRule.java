package com.uiautomator.lib.support.rule;

import androidx.test.uiautomator.UiDevice;

import com.uiautomator.lib.support.context.TestContext;
import com.uiautomator.lib.support.network.MultiFile;
import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.util.HttpRequestUtil;
import com.uiautomator.lib.support.util.ScreenShot;
import com.uiautomator.lib.support.util.exception.ExceptionHelper;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestWatcherRule extends TestWatcher {
    public UiDevice device;
    public int shotCount;
    public TestWatcherRule(UiDevice device) {
        this.device = device;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        super.failed(e, description);
        byte[] shot = TestContext.getInstance().screenShot(100);
        MultiFile multiFile = new MultiFile("shot",shot,"image/jpeg");
        Map<String, MultiFile> m = new HashMap<>();
        m.put("source", multiFile);
        try {
            MyRequest signRequest = HttpRequestUtil.buildPostRequest("http", "192.168.16.2", "/api/v1/img/temp/upload", "8085", "utf-8", null,m);
            byte[] result = HttpRequestUtil.request(signRequest);
            String img = new String(result);
            Map<String, String> toast = TestContext.getInstance().getToastMessage();
            StackTraceElement stackTraceElement = new StackTraceElement(TestWatcherRule.class.getName(), "failed", "截图："+img+":toast:"+toast, 37);
            ExceptionHelper.cutStacktrace(e, 6, stackTraceElement);
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

    public void takeShot(Throwable e, Description description){
        String methodName = description.getMethodName();
        String className = description.getClassName();
        String fileName = String.format("%s_%s_%d_%d.png", className, methodName, System.currentTimeMillis(), shotCount);
        if(ScreenShot.screenShot(device, fileName)){
           shotCount++;
        }
    }
}
