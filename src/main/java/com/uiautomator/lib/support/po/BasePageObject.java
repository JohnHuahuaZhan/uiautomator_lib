package com.uiautomator.lib.support.po;

import android.graphics.Point;
import android.graphics.Rect;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import com.uiautomator.lib.support.conditions.Condition;
import com.uiautomator.lib.support.context.TestContext;
import com.uiautomator.lib.support.exception.UIAutomatorTestException;
import com.uiautomator.lib.support.log.UIAutomatorLogger;
import com.uiautomator.lib.support.time.IParamProvider;
import com.uiautomator.lib.support.time.Sleeper;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

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

    public BasePageObject(String packageName, long find_timeout, long pollingEvery) {
        this.packageName = packageName;
        this.find_timeout = find_timeout;
        this.pollingEvery = pollingEvery;
    }

    public long getPollingEvery() {
        return pollingEvery;
    }

    public void setPollingEvery(long pollingEvery) {
        this.pollingEvery = pollingEvery;
    }

    public BasePageObject() {
    }

    public UiSelector us(){
        return new UiSelector();
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
    protected UiObject2 mustFindObject(BySelector by, long timeout, String message){
        UiObject2 object = findObject(by, timeout);
        if(null == object)
            throw  new UIAutomatorTestException(message);
        return object;
    }
    protected UiObject2 mustFindObject(BySelector by, String message){
        UiObject2 object = findObject(by);
        if(null == object)
            throw  new UIAutomatorTestException(message);
        return object;
    }
    protected UiObject2 findObject(BySelector by, long timeout, Runnable runnable){
         return Condition.waitForCondition(()->{
            return device;
        }, by,  pollingEvery, timeout, runnable);

    }
    protected UiObject2 findObject(BySelector by, long timeout, Runnable runnable, IParamProvider<Boolean, UiObject2>  provider){
       return  Condition.waitForCondition(()->{
            return device;
        }, by,  pollingEvery, timeout, runnable, provider);

    }









    /**
     * 返回一个找到的UIObject2列表，如果找不到返回空列表
     * @param by
     * @return
     */
    protected List<UiObject2> findObjects(UiObject2 parent, BySelector by) {
        return parent.findObjects(by);
    }
    protected List<UiObject2> findObjects(UiObject2 parent,Matcher<Integer> matcher, BySelector by,  long timeout, Runnable runnable) {
        boolean b = Condition.waitForCondition(matcher,()-> findObjects(parent,by).size(),  pollingEvery, timeout, runnable);
        if(!b)
            return null;
        else
            return findObjects(parent,by);
    }
    /**
     * 返回一个找到的UIObject2列表，如果找不到指定个数而超时返回null
     * @param by
     * @return
     */
    protected List<UiObject2> findObjects(UiObject2 parent,BySelector by, int expectedSize, long timeout, Runnable runnable) {
        return findObjects(parent, CoreMatchers.equalTo(expectedSize), by, timeout, runnable);
    }

    /**
     * 返回一个找到的UIObject2列表，如果找不到指定个数而超时返回null
     * @param by
     * @return
     */
    protected List<UiObject2> findObjects(UiObject2 parent,BySelector by, int expectedSize, long timeout) {
        return findObjects(parent, by, expectedSize,timeout, ()->{});
    }

    protected List<UiObject2> mustFindObjects(UiObject2 parent,Matcher<Integer> matcher, BySelector by,  long timeout, Runnable runnable, String message) {
        List<UiObject2> result = findObjects(parent, matcher, by, timeout, runnable);
        if(null == result)
            throw  new UIAutomatorTestException(message);

        return result;
    }
    protected List<UiObject2> mustFindObjects(UiObject2 parent, Matcher<Integer> matcher, BySelector by,  long timeout,  String message) {
        return mustFindObjects(parent, matcher, by, timeout, ()->{},message);
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
     * 自定义你的匹配器。如找到的的个数要大于某个值则使用 findObjects(Matchers.greaterThan(0),  by,  timeout)
     * @param matcher
     * @param by
     * @param timeout
     * @return
     */
    protected List<UiObject2> findObjects(Matcher<Integer> matcher, BySelector by,  long timeout) {
        boolean b = Condition.waitForCondition(matcher,()-> findObjects(by).size(),  pollingEvery, timeout, ()->{});
        if(!b)
            return null;
        else
            return findObjects(by);
    }
    protected List<UiObject2> mustFindObjects(Matcher<Integer> matcher, BySelector by,  long timeout, Runnable runnable, String message) {
        List<UiObject2> result = findObjects(matcher, by, timeout, runnable);
        if(null == result)
            throw  new UIAutomatorTestException(message);

        return result;
    }
    protected List<UiObject2> mustFindObjects(Matcher<Integer> matcher, BySelector by,  long timeout,  String message) {
       return mustFindObjects(matcher, by, timeout, ()->{},message);
    }
    protected List<UiObject2> findObjects(Matcher<Integer> matcher, BySelector by,  long timeout, Runnable runnable) {
        boolean b = Condition.waitForCondition(matcher,()-> findObjects(by).size(),  pollingEvery, timeout, runnable);
        if(!b)
            return null;
        else
            return findObjects(by);
    }
    /**
     * 返回一个找到的UIObject2列表，如果找不到指定个数而超时返回null
     * @param by
     * @return
     */
    protected List<UiObject2> findObjects(BySelector by, int expectedSize, long timeout, Runnable runnable) {
        return findObjects(CoreMatchers.equalTo(expectedSize), by, timeout, runnable);
    }
    /**
     * 返回一个找到的UIObject2列表，如果找不到返回null
     * @param by
     * @return
     */
    protected List<UiObject2> findObjects(BySelector by, int expectedSize, long timeout) {
        return findObjects(by, expectedSize, timeout,()->{});
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
     * 返回一个找到的UIObject2，如果找不到返回null
     * @param by
     * @return
     */
    protected UiObject2 mustFindObject(UiObject2 uiObject2, BySelector by, String message){
        UiObject2 object = uiObject2.findObject(by);
        if(null == object)
            throw  new UIAutomatorTestException(message);
        return object;
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
    protected UiObject2 mustFindObject(UiObject2 uiObject2, BySelector by, long timeout, String message){
        UiObject2 object = findObject(uiObject2, by, timeout);
        if(null == object)
            throw  new UIAutomatorTestException(message);
        return object;
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

    public boolean clickIgnoreException(UiObject object){
        try {
            object.click();
            return true;
        } catch (UiObjectNotFoundException e) {
            UIAutomatorLogger.e(e.getMessage());
            return false;
        }
    }
    public boolean textIgnoreException(UiObject object, String text){
        try {
            object.setText(text);
            return true;
        } catch (UiObjectNotFoundException e) {
            UIAutomatorLogger.e(e.getMessage());
            return false;
        }
    }
    public String getTextIgnoreException(UiObject object){
        try {
            return object.getText();
        } catch (UiObjectNotFoundException e) {
            UIAutomatorLogger.e(e.getMessage());
            return null;
        }
    }
    public boolean swipeDownIgnoreException(UiObject object, int steps){
        try {
            return object.swipeDown(steps);
        } catch (UiObjectNotFoundException e) {
            UIAutomatorLogger.e(e.getMessage());
            return false;
        }
    }
    public boolean swipeUpIgnoreException(UiObject object, int steps){
        try {
            return object.swipeUp(steps);
        } catch (UiObjectNotFoundException e) {
            UIAutomatorLogger.e(e.getMessage());
            return false;
        }
    }

    public void swipeDown(UiObject2 object, float percent, int speed){
        object.swipe(Direction.DOWN, percent, speed);
    }
    public void swipeDown(UiObject2 object, float percent){
        object.swipe(Direction.DOWN, percent);
    }

    public void swipeUp(UiObject2 object, float percent, int speed){
        object.swipe(Direction.UP, percent, speed);
    }
    public void swipeUp(UiObject2 object,  float percent){
        object.swipe(Direction.UP, percent);
    }

    //swipe


    /**
     * swipe from x,y.
     * @param direction
     * @param pixels
     * @param speed pixel per second
     */
    public  void swipe(int x,int  y, Direction direction, int pixels, int speed){

        double  time = (double) pixels / (double)speed;
        int step = (int) (time * 1000 / 5);
        int ex = x;
        int ey = y;

        switch (direction){
            case UP:
                ey = y - pixels;
                break;
            case DOWN:
                ey = y + pixels;
                break;
            case LEFT:
                ex = x - pixels;
                break;
            case RIGHT:
                ex = x + pixels;
                break;
            default:
                break;
        }

        getDevice().swipe(x,y,ex,ey,step);
    }

    /**
     * swipe from point to point
     * @param start
     * @param end
     * @param speed
     */
    public  void swipe(Point start,Point end,int speed){
        int pixels = (int) Math.sqrt(Math.pow(end.x - start.x,2)+ Math.pow(end.y - start.y,2));
        double  time = (double) pixels / (double)speed;
        int step = (int) (time * 1000 / 5);
        getDevice().swipe(start.x,start.y,end.x,end.y,step);
    }
    public static enum START_POINT{
        LEFT_TOP,
        RIGHT_TOP,
        CENTER,
        LEFT_BOTTOM,
        RIGHT_BOTTOM
    }
    /**
     * swipe from uiObject2 start_point
     * @param object2
     * @param pixel 像素
     * @param pixelSpeed pixel per second
     */
    public  void swipe(UiObject2 object2,int pixel,int pixelSpeed,Direction direction,START_POINT start_point){
        Rect rect = object2.getVisibleBounds();


        int x = rect.centerX();
        int y = rect.centerY();
        switch (start_point){
            case LEFT_TOP:
                x = rect.left;
                y = rect.top;
                break;
            case RIGHT_TOP:
                x = rect.right;
                y = rect.top;
                break;
            case CENTER:
                x = rect.centerX();
                y = rect.centerY();
                break;
            case LEFT_BOTTOM:
                x = rect.left;
                y = rect.bottom;
                break;
            case RIGHT_BOTTOM:
                x = rect.right;
                y = rect.bottom;
                break;
            default:
                break;
        }

        swipe(x,y,direction,pixel,pixelSpeed);

    }
    /**
     * swipe from uiObject2 start_point
     * @param object2
     * @param times 几个对象高度
     * @param speed 每秒几个对象
     */
    public  void swipe(UiObject2 object2,int times,double speed,Direction direction,START_POINT start_point){
        int pixel = object2.getVisibleBounds().height() * times;
        int pixelSpeed = (int) (object2.getVisibleBounds().height() * speed);
        if(direction == Direction.LEFT || direction == Direction.RIGHT){
            pixel = object2.getVisibleBounds().width() * times;
            pixelSpeed = (int) (object2.getVisibleBounds().width() * speed);
        }
        swipe(object2, pixel, pixelSpeed, direction, start_point);
    }
    public  Point getPointByStartPoint(UiObject2 object,START_POINT start_point){
        Rect rect = object.getVisibleBounds();
        int ex = rect.centerX();
        int ey = rect.centerY();
        switch (start_point){
            case LEFT_TOP:
                ex = rect.left;
                ey = rect.top;
                break;
            case RIGHT_TOP:
                ex = rect.right;
                ey = rect.top;
                break;
            case CENTER:
                ex = rect.centerX();
                ey = rect.centerY();
                break;
            case LEFT_BOTTOM:
                ex = rect.left;
                ey = rect.bottom;
                break;
            case RIGHT_BOTTOM:
                ex = rect.right;
                ey = rect.bottom;
                break;
            default:
                break;
        }
        return new Point(ex,ey);
    }


    /**
     * swipe from p 2 p.
     * @param start
     * @param end
     * @param pixelSpeed
     * @param start_point
     * @param start_point_end pixel per second
     */
    public  void swipe(UiObject2 start,UiObject2 end,int pixelSpeed,START_POINT start_point,START_POINT start_point_end){
        Point startPoint = getPointByStartPoint(start,start_point);
        Point endPoint = getPointByStartPoint(end,start_point_end);
        swipe(startPoint, endPoint,pixelSpeed);
    }

    /**
     * swipe from p 2 p.
     * @param start
     * @param end
     * @param speedObjectHigh 速度参考对象。高度作为参考
     * @param speed 几个参考对象高度每秒
     * @param start_point
     * @param start_point_end
     */
    public  void swipe(UiObject2 start,UiObject2 end,UiObject2 speedObjectHigh,double speed,START_POINT start_point,START_POINT start_point_end){
        int pixelSpeed = (int) (speedObjectHigh.getVisibleBounds().height() * speed);
        Point startPoint = getPointByStartPoint(start,start_point);
        Point endPoint = getPointByStartPoint(end,start_point_end);
        swipe(startPoint, endPoint,pixelSpeed);
    }
    /**
     * swipe from center
     * @param direction
     * @param pixels
     * @param speed pixel per second
     */
    public  void swipe(Direction direction, int pixels, int speed){
        int x = getDevice().getDisplayWidth() / 2;
        int y = getDevice().getDisplayHeight() / 2;
        swipe(x,y,direction,pixels,speed);
    }

    /**
     * swipe from center
     * @param direction
     * @param times 几个对象高度
     * @param speed 每秒几个对象
     */
    public  void swipe(UiObject2 object2,Direction direction, int times, double speed){
        int x = device.getDisplayWidth() / 2;
        int y = device.getDisplayHeight() / 2;
        int pixel = object2.getVisibleBounds().height() * times;
        int pixelSpeed = (int) (object2.getVisibleBounds().height() * speed);
        if(direction == Direction.LEFT || direction == Direction.RIGHT){
            pixel = object2.getVisibleBounds().width() * times;
            pixelSpeed = (int) (object2.getVisibleBounds().width() * speed);
        }
        swipe(x,y,direction,pixel,pixelSpeed);
    }

    public void systemWait(long milli){
        try {
            Sleeper.SYSTEM_SLEEPER.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checked(UiObject2 uiObject2){
        if(!uiObject2.isChecked())
            uiObject2.click();
    }
    public void uncheck(UiObject2 uiObject2){
        if(uiObject2.isChecked())
            uiObject2.click();
    }
    public void mustChecked(UiObject2 uiObject2, String message){
        checked(uiObject2);
        if(!uiObject2.isChecked())
            throw  new UIAutomatorTestException(message);
    }
    public void mustUncheck(UiObject2 uiObject2, String message){
        uncheck(uiObject2);
        if(uiObject2.isChecked())
            throw  new UIAutomatorTestException(message);
    }
}
