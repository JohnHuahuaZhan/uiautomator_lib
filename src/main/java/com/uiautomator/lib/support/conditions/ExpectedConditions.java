/**
 * @author 菜花
 * @date 20190524
 */
package com.uiautomator.lib.support.conditions;

import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;

import com.uiautomator.lib.support.interfaces.Function;
import com.uiautomator.lib.support.time.IParamProvider;

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

    public  static  Function<UiDevice, UiObject2> find(BySelector bySelector){
        return new Function<UiDevice, UiObject2>() {
            @Override
            public UiObject2 apply(UiDevice t) {
                return t.findObject(bySelector);
            }
        };
    }

    public  static Function<UiDevice, UiObject2> find(BySelector bySelector, IParamProvider<Boolean, UiObject2> provider ){
        return new Function<UiDevice, UiObject2>() {
            @Override
            public UiObject2 apply(UiDevice t) {
                UiObject2 object2 = t.findObject(bySelector);
                if(provider.get(object2))
                    return object2;
                return null;
            }
        };
    }
}
