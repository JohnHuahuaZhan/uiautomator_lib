/**
 * @author 菜花
 * @date 20190524
 */
package com.uiautomator.lib.support.conditions;

import com.uiautomator.lib.support.interfaces.Function;

import org.hamcrest.Matcher;


public class ExpectedConditions {
    public  static <T> Function<T, Boolean> match(Matcher<T> matcher){
       return new Function<T, Boolean>() {
           @Override
           public Boolean apply(T t) {
               return matcher.matches(t);
           }
       };
    }
}
