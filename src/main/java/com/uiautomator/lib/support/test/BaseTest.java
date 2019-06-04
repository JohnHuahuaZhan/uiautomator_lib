package com.uiautomator.lib.support.test;

import android.content.Intent;
import android.os.RemoteException;

import androidx.test.uiautomator.UiDevice;

import com.uiautomator.lib.support.asserts.TestAssert;
import com.uiautomator.lib.support.conditions.ExpectedConditions;
import com.uiautomator.lib.support.context.TestContext;
import com.uiautomator.lib.support.log.UIAutomatorLogger;
import com.uiautomator.lib.support.time.FluentWait;
import com.uiautomator.lib.support.time.IProvider;
import com.uiautomator.lib.support.time.Sleeper;
import com.uiautomator.lib.support.util.ScreenShot;

import org.hamcrest.Matcher;

import java.io.IOException;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class BaseTest {
    public static final long defaultPollingEvery = 1000;


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
    public void startApp(String packageName){
        final Intent intent = testContext.getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        testContext.startActivity(intent);
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
        FluentWait<T> fluentWait = new FluentWait<>(actual);
        fluentWait.pollingEvery(defaultPollingEvery).withTimeout(timeout);
        return fluentWait.until(ExpectedConditions.match(matcher));
    }

    public <T>void assertThat(String reason, T actual, Matcher<? super T> matcher, Runnable callback){
        TestAssert.assertThat(reason, actual, matcher, callback);
    }
    public <T>void assertThat(String reason, T actual, Matcher<? super T> matcher){
        TestAssert.assertThat(reason, actual, matcher, ()->{});
    }
}
