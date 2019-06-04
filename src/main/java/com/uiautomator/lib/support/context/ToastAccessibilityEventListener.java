package com.uiautomator.lib.support.context;

import android.app.Notification;
import android.app.UiAutomation;
import android.os.Parcelable;
import android.view.accessibility.AccessibilityEvent;

import com.uiautomator.lib.support.log.UIAutomatorLogger;

public class ToastAccessibilityEventListener implements UiAutomation.OnAccessibilityEventListener {
    private String toastMessage;

    public String getToastMessage() {
        return toastMessage;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //判断是否是通知事件
        if (event.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            return;
        }
        //获取消息来源
        String sourcePackageName = (String) event.getPackageName();
        //获取事件具体信息
        Parcelable parcelable = event.getParcelableData();
        //如果不是下拉通知栏消息，则为其它通知信息，包括Toast
        if (!(parcelable instanceof Notification)) {
            toastMessage = (String) event.getText().get(0);
            UIAutomatorLogger.d(toastMessage+Thread.currentThread().getId());
        }
    }
}
