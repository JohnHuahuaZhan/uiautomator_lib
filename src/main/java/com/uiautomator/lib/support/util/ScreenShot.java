package com.uiautomator.lib.support.util;

import androidx.test.uiautomator.UiDevice;

import com.uiautomator.lib.support.context.TestContext;
import com.uiautomator.lib.support.log.UIAutomatorLogger;

import java.io.File;

public class ScreenShot {
    public static final String SHOT_PATH = "/UIAUTOMATOR_SCREEN_SHOT";
    /**
     * 截图失败返回false
     * @return
     */
    public static boolean screenShot(UiDevice device, String fileName){
        String path = TestContext.getInstance().getExternalStorageDirectory()+ SHOT_PATH;
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            boolean success = pathFile.mkdirs();
            if(!success){
                UIAutomatorLogger.e("创建目录失败:"+path);
                return false;
            }
        }
        File shot = new File(String.format("%s/%s", path, fileName));
        return device.takeScreenshot(shot);
    }
}
