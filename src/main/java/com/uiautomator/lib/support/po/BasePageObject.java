package com.uiautomator.lib.support.po;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import com.uiautomator.lib.support.conditions.Condition;
import com.uiautomator.lib.support.context.TestContext;

import java.util.List;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class BasePageObject {
    protected UiDevice device = UiDevice.getInstance(getInstrumentation());
    protected TestContext context = TestContext.getInstance();

    public TestContext getContext() {
        return context;
    }

    public UiDevice getDevice() {
        return device;
    }
    protected String packageName;
    protected long find_timeout;
    protected long pollingEvery;
    public BasePageObject(String packageName, long find_timeout) {
        this.packageName = packageName;
        this.find_timeout = find_timeout;
    }

    public BasePageObject(String packageName, long find_timeout, long defaultPollingEvery) {
        this.packageName = packageName;
        this.find_timeout = find_timeout;
        this.pollingEvery = defaultPollingEvery;
    }

    public long getPollingEvery() {
        return pollingEvery;
    }

    public void setPollingEvery(long pollingEvery) {
        this.pollingEvery = pollingEvery;
    }

    public BasePageObject() {
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getFind_timeout() {
        return find_timeout;
    }

    public void setFind_timeout(long find_timeout) {
        this.find_timeout = find_timeout;
    }
    /**
     * 返回一个找到的UIObject2，如果找不到返回null
     * @param by
     * @return
     */
    protected UiObject2 findObject(BySelector by){
        return device.findObject(by);
    }

    /**
     * 返回一个找到的UIObject2，一秒找一次。超时返回null
     * @param by
     * @param timeout
     * @return
     */
    protected UiObject2 findObject(BySelector by, long timeout){
        return device.wait(Until.findObject(by), timeout);
    }
    protected UiObject2 findObject(BySelector by, long timeout, Runnable runnable){
        Condition.waitForCondition(()->{
            return device;
        }, by,  pollingEvery, timeout, runnable);
        return device.wait(Until.findObject(by), timeout);
    }
    /**
     * 返回一个找到的UIObject2列表，如果找不到返回空列表
     * @param by
     * @return
     */
    protected List<UiObject2> findObjects(BySelector by) {
        return device.findObjects(by);
    }




        /**
         * 返回一个带有选择器的UIObject
         * @param uiSelector
         * @return
         */
    protected UiObject findObject(UiSelector uiSelector){
        return device.findObject(uiSelector);
    }
    /**
     * 返回一个带有选择器的UIObject.一秒找一次。超时返回null
     * @param uiSelector
     * @return
     */
    protected UiObject findObject(UiSelector uiSelector, long timeout){
        UiObject object = device.findObject(uiSelector);
        boolean finds = object.waitForExists(timeout);
        return finds == true? object:null;
    }


    /**
     * 返回一个找到的UIObject2，如果找不到返回null
     * @param by
     * @return
     */
    protected UiObject2 findObject(UiObject2 uiObject2, BySelector by){
        return uiObject2.findObject(by);
    }

    /**
     * 返回一个找到的UIObject2，一秒找一次。超时返回null
     * @param by
     * @param timeout
     * @return
     */
    protected UiObject2 findObject(UiObject2 uiObject2, BySelector by, long timeout){
        return uiObject2.wait(Until.findObject(by), timeout);
    }
    /**
     * 返回一个找到的UIObject2列表，如果找不到返回空列表
     * @param by
     * @return
     */
    protected List<UiObject2> findObjects(UiObject2 uiObject2,BySelector by) {
        return uiObject2.findObjects(by);
    }

    protected UiScrollable uiScrollable(UiSelector uiSelector){
        return new UiScrollable(uiSelector);
    }

    public String resString(String resourcePackage, String resourceId){
        return String.format("%s:id/%s", resourcePackage, resourceId);
    }
    public UiSelector res(String resourcePackage, String resourceId){
        return new UiSelector().resourceId(resString(resourcePackage, resourceId));
    }
    public BySelector resBy(String resourcePackage, String resourceId){
        return By.res(resString(resourcePackage, resourceId));
    }
}
