package com.uiautomator.lib.support.context;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiWatcher;

public class DefaultWatchers {
    public static final class CommonIdPermissionWatcher implements UiWatcher{
        public CommonIdPermissionWatcher(UiDevice uiDevice) {
            this.uiDevice = uiDevice;
        }

        UiDevice uiDevice;
        static BySelector commonId = By.res("com.android.packageinstaller:id/permission_allow_button");
        @Override
        public boolean checkForCondition() {
            UiObject2 object2 = uiDevice.findObject(commonId);
            if(object2 != null){
                object2.click();
                return true;
            }
            return false;
        }
    }

    public static final class ClickTextWatcher implements UiWatcher{

        UiDevice uiDevice;
        BySelector commonId;
        String text;

        public ClickTextWatcher(UiDevice uiDevice, String text) {
            this.uiDevice = uiDevice;
            this.text = text;
            commonId = By.text(text);
        }

        @Override
        public boolean checkForCondition() {
            UiObject2 object2 = uiDevice.findObject(commonId);
            if(object2 != null){
                object2.click();
                return true;
            }
            return false;
        }
    }
}
