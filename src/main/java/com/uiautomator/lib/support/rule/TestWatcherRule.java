package com.uiautomator.lib.support.rule;

import androidx.test.uiautomator.UiDevice;

import com.uiautomator.lib.support.util.ScreenShot;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TestWatcherRule extends TestWatcher {
    public UiDevice device;
    public int shotCount;
    public TestWatcherRule(UiDevice device) {
        this.device = device;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        super.failed(e, description);

        takeShot(e, description);

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
