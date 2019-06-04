/**
 * @author 菜花
 * @date 20190524
 */
package com.uiautomator.lib.support.time;


import com.uiautomator.lib.support.interfaces.Function;

public interface Wait<F> {
    <T> T until(Function<? super F, T> function);
}
