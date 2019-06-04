/**
 * @author 菜花
 * @date 20190524
 */
package com.uiautomator.lib.support.time;

public interface Sleeper {

    Sleeper SYSTEM_SLEEPER = duration -> Thread.sleep(duration);

    /**
     * Sleeps for the specified duration of time.
     * @param duration 毫秒
     * @throws InterruptedException
     */
    void sleep(long duration) throws InterruptedException;
}