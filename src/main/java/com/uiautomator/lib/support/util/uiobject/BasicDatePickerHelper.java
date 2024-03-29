package com.uiautomator.lib.support.util.uiobject;

import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiObject2;

import com.uiautomator.lib.support.exception.UIAutomatorTestException;
import com.uiautomator.lib.support.log.UIAutomatorLogger;
import com.uiautomator.lib.support.po.BasePageObject;
import com.uiautomator.lib.support.time.IProvider;
import com.uiautomator.lib.support.time.Sleeper;

/**
 *    BasicDatePickerHelper basicDatePickerHelper = new BasicDatePickerHelper();
 *         basicDatePickerHelper.setMaxRetry(30);
 *         basicDatePickerHelper.setYearProvider(()->{return getDevice().findObjects(By.res("android:id/numberpicker_input")).get(0);});
 *         basicDatePickerHelper.setMonthProvider(()->{return getDevice().findObjects(By.res("android:id/numberpicker_input")).get(1);});
 *         basicDatePickerHelper.setDayProvider(()->{return getDevice().findObjects(By.res("android:id/numberpicker_input")).get(2);});
 *         basicDatePickerHelper.setAcceptProvider(()->{return getDevice().findObject(By.res("android:id/button1"));});
 *         basicDatePickerHelper.setCancelProvider(()->{return getDevice().findObject(By.res("android:id/button2"));});
 *         basicDatePickerHelper.setMargin(-20);
 *         basicDatePickerHelper.setSpeed(500);
 *         basicDatePickerHelper.setYear(2013);
 *         basicDatePickerHelper.setMonth(9);
 *         basicDatePickerHelper.setDay(26);
 *         basicDatePickerHelper.selectYear().selectMonth().selectDay().accept();
 */
public  class BasicDatePickerHelper extends BasePageObject {

    int margin;
    int maxRetry;
    int speed;
    IProvider<UiObject2> yearProvider;
    IProvider<UiObject2> monthProvider;
    IProvider<UiObject2> dayProvider;
    IProvider<UiObject2> acceptProvider;
    IProvider<UiObject2> cancelProvider;
    int year;
    int month;
    int day;


    public void setMargin(int margin) {
        this.margin = margin;
    }

    public void setMaxRetry(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setYearProvider(IProvider<UiObject2> yearProvider) {
        this.yearProvider = yearProvider;
    }

    public void setMonthProvider(IProvider<UiObject2> monthProvider) {
        this.monthProvider = monthProvider;
    }

    public void setDayProvider(IProvider<UiObject2> dayProvider) {
        this.dayProvider = dayProvider;
    }

    public void setAcceptProvider(IProvider<UiObject2> acceptProvider) {
        this.acceptProvider = acceptProvider;
    }

    public void setCancelProvider(IProvider<UiObject2> cancelProvider) {
        this.cancelProvider = cancelProvider;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMargin() {
        return margin;
    }

    public int getMaxRetry() {
        return maxRetry;
    }

    public IProvider<UiObject2> getYearProvider() {
        return yearProvider;
    }

    public IProvider<UiObject2> getMonthProvider() {
        return monthProvider;
    }

    public IProvider<UiObject2> getDayProvider() {
        return dayProvider;
    }

    public IProvider<UiObject2> getAcceptProvider() {
        return acceptProvider;
    }

    public IProvider<UiObject2> getCancelProvider() {
        return cancelProvider;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }


    private void swipeDown(IProvider<UiObject2> provider){
        UiObject2 object = provider.get();
        object.setGestureMargin(margin);
        object.swipe(Direction.DOWN, 1, speed);
    }
    private void swipeUp(IProvider<UiObject2> provider){
        UiObject2 object = provider.get();
        object.setGestureMargin(margin);
        object.swipe(Direction.UP, 1, speed);
    }
    private String getText(IProvider<UiObject2> provider){
        return provider.get().getText();
    }
    private void select(IProvider<UiObject2> provider, int target){
        boolean backward = true;

        int count = 0;
        int current = Integer.valueOf(getText(provider));
        int last = current;
        while (true){
            current = Integer.valueOf(getText(provider));
            if(last == current){//预防滚动后由于值没有及时设置到框中，导致读取到的还是上一次的值。
                try {
                    Sleeper.SYSTEM_SLEEPER.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                current = Integer.valueOf(getText(provider));
            }
            last = current;
            UIAutomatorLogger.d(current+"year");
            if(current == target)
                return;
            int compare = current - target;
            if(compare == 0)
                return;
            if(compare < 0)
                backward = false;
            if(backward){
                UIAutomatorLogger.d("scroll year back");
                swipeDown(provider);
            }else { UIAutomatorLogger.d("scroll year forward");
                swipeUp(provider);
            }

            count++;
            if(count >= maxRetry)
                throw new UIAutomatorTestException("DatePicker 不能到达指定位置："+target);

        }
    }
    public BasicDatePickerHelper  selectYear(){
        select(yearProvider, this.year);
        return this;
    }
    public BasicDatePickerHelper selectMonth(){
        select(monthProvider, this.month);
        return this;
    }
    public BasicDatePickerHelper selectDay(){
        select(dayProvider, this.day);
        return this;
    }
    public BasicDatePickerHelper accept(){
        acceptProvider.get().click();
        return this;
    }
    public BasicDatePickerHelper cancel(){
        cancelProvider.get().click();
        return this;
    }
}