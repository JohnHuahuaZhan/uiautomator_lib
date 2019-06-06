package com.uiautomator.lib.support.conditions;

import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;

import com.uiautomator.lib.support.time.FluentWait;
import com.uiautomator.lib.support.time.IProvider;

import org.hamcrest.Matcher;

public class Condition {
    public static  <T> Boolean waitForCondition(Matcher<T> matcher, IProvider<T> actual,long  pollingEvery, long timeout){
       return waitForCondition(matcher, actual, pollingEvery, timeout, ()->{});
    }
    public static  <T> Boolean waitForCondition(Matcher<T> matcher, IProvider<T> actual,long  pollingEvery, long timeout, Runnable runnable){
        FluentWait<T> fluentWait = new FluentWait<>(actual);
        fluentWait.pollingEvery(pollingEvery).withTimeout(timeout);
        return fluentWait.until(ExpectedConditions.match(matcher), runnable);
    }
    public static UiObject2 waitForCondition(IProvider<UiDevice> actual, BySelector bySelector, long  pollingEvery, long timeout, Runnable runnable){
        FluentWait<UiDevice> fluentWait = new FluentWait<>(actual);
        fluentWait.pollingEvery(pollingEvery).withTimeout(timeout);
        return fluentWait.until(ExpectedConditions.find(bySelector), runnable);
    }
    public static UiObject2 waitForCondition(IProvider<UiDevice> actual, BySelector bySelector, long  pollingEvery, long timeout, Runnable runnable, IProvider<Boolean> provider){
        FluentWait<UiDevice> fluentWait = new FluentWait<>(actual);
        fluentWait.pollingEvery(pollingEvery).withTimeout(timeout);
        return fluentWait.until(ExpectedConditions.find(bySelector, provider), runnable);
    }
}
