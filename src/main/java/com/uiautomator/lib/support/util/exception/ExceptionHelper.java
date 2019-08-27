package com.uiautomator.lib.support.util.exception;

public class ExceptionHelper {
    public static void cutStacktrace(Throwable e, int count, StackTraceElement ... stackTraceElements){
        StackTraceElement[] source = e.getStackTrace();
        count = source.length >= count? count : source.length;
        StackTraceElement[] result = new StackTraceElement[count + stackTraceElements.length];
        for (int i = 0; i < count + stackTraceElements.length; i++) {
            if(i < count)
                result[i] = source[i];
            else
                result[i] = stackTraceElements[i - count];
        }
        e.setStackTrace(result);
    }
}
