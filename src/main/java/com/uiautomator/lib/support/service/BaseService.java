package com.uiautomator.lib.support.service;

import android.content.Intent;
import android.os.RemoteException;

import androidx.test.uiautomator.UiDevice;

import com.uiautomator.lib.support.asserts.TestAssert;
import com.uiautomator.lib.support.conditions.Condition;
import com.uiautomator.lib.support.context.TestContext;
import com.uiautomator.lib.support.log.UIAutomatorLogger;
import com.uiautomator.lib.support.time.IProvider;
import com.uiautomator.lib.support.time.Sleeper;
import com.uiautomator.lib.support.util.ScreenShot;

import org.hamcrest.Matcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class BaseService {
    public static final long defaultPollingEvery = 500;
    public static final long defaultTimeout = 8000;
    protected String tag;
    protected String memo;

    public BaseService(String tag, String memo) {
        this.tag = tag;
        this.memo = memo;
    }
    public BaseService() {

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    private UiDevice device = UiDevice.getInstance(getInstrumentation());
    private TestContext testContext = TestContext.getInstance();
    public UiDevice getDevice() {
        return device;
    }

    public TestContext getContext() {
        return testContext;
    }

    public void home(){
        device.pressHome();
    }

    public void back(){
        device.pressBack();
    }

    /**
     * 成功返回true
     * @return
     */
    public boolean recentApp(){
        try {
            device.pressRecentApps();
            return true;
        } catch (RemoteException e) {
            UIAutomatorLogger.e(e.getMessage());
            return false;
        }
    }
    /**
     * 失败返回null
     * @return
     */
    public String executeShell(String shell){
        try {
            return device.executeShellCommand(shell);
        } catch (IOException e) {
            UIAutomatorLogger.e(e.getMessage());
            return null;
        }
    }
    public String grantPermission(String packageName, String ... permissions){
        StringBuffer stringBuffer = new StringBuffer("pm grant "+packageName);
        if (null != permissions && permissions.length > 0){
            for (String permission : permissions) {
                stringBuffer.append(String.format(" %s", permission));
            }
        }
        return  executeShell(stringBuffer.toString());
    }
    public void startApp(String packageName){
        final Intent intent = testContext.getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);    // Clear out any previous instances
        testContext.startActivity(intent);
    }

    /**
     * 兼容
     * @param packageName
     * @param mainActivity
     * @return
     */
    public String startApp(String packageName, String  mainActivity){
        return executeShell(String.format("am start %s/%s", packageName, mainActivity));
    }
    /**
     * since api 21
     * @param packageName
     * @return
     */
    public String stopApp(String packageName){
       return executeShell(String.format("am force-stop %s", packageName));
    }
    /**
     * since api 21
     * @param packageName
     * @return
     */
    public String stopCleanApp(String packageName){
        return executeShell(String.format("pm clear %s", packageName));
    }

    /**
     * dump 失败返回null
     * @return
     */
    public byte[] dump(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            getDevice().dumpWindowHierarchy(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            UIAutomatorLogger.e("dump failed");
            return null;
        }
    }
    /**
     * 截图失败返回false
     * @return
     */
    public boolean screenShot(String fileName){

        return ScreenShot.screenShot(device, fileName);
    }
    public void systemWait(long milli){
        try {
            Sleeper.SYSTEM_SLEEPER.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public <T> Boolean waitForCondition(Matcher<T> matcher, IProvider<T> actual, long timeout){
        return waitForCondition(matcher, actual, defaultPollingEvery, timeout);
    }
    public <T> Boolean waitForCondition(Matcher<T> matcher, IProvider<T> actual, long pollingEvery, long timeout){
        return Condition.waitForCondition(matcher, actual, pollingEvery, timeout);
    }
    public <T>void assertThat(String reason, T actual, Matcher<? super T> matcher, Runnable callback){
        TestAssert.assertThat(reason, actual, matcher, callback);
    }
    public <T>void assertThat(String reason, T actual, Matcher<? super T> matcher){
        TestAssert.assertThat(reason, actual, matcher, ()->{});
    }
}
