package com.uiautomator.lib.support.context;

import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.Configurator;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;

import com.uiautomator.lib.support.log.UIAutomatorLogger;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class TestContext {
    private Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    /**
     * 暴露UIDevice中的一些方法
     */
    static final int API_LEVEL_ACTUAL = Build.VERSION.SDK_INT
            + ("REL".equals(Build.VERSION.CODENAME) ? 0 : 1);
    static final int DEFAULT_UIAUTOMATION_FLAGS = 0;
    UiAutomation getUiAutomation() {
        int flags = Configurator.getInstance().getUiAutomationFlags();
        if (API_LEVEL_ACTUAL > Build.VERSION_CODES.M) {
            return instrumentation.getUiAutomation(flags);
        } else {
            // Custom flags not supported prior to N.
            if (flags != DEFAULT_UIAUTOMATION_FLAGS) {
                UIAutomatorLogger.e( "UiAutomation flags not supported prior to N - ignoring.");
            }
            return instrumentation.getUiAutomation();
        }
    }











    private static final TestContext ourInstance = new TestContext();
    private ToastAccessibilityEventListener toastAccessibilityEventListener = new ToastAccessibilityEventListener();
    public static TestContext getInstance() {
        return ourInstance;
    }
    Context context = ApplicationProvider.getApplicationContext();
    private TestContext() {
        getInstrumentation().getUiAutomation().setOnAccessibilityEventListener(toastAccessibilityEventListener);
    }


    public Map<String, String> getToastMessage(){
        String pkg =  toastAccessibilityEventListener.getToastMessage().packageName;
        String message =  toastAccessibilityEventListener.getToastMessage().message;
        Map<String, String> map = new HashMap<>();
        map.put("package", pkg);
        map.put("message", message);
        return map;
    }
    public String getStringResources(int id){
        return context.getResources().getString(id);
    }
    public Intent getLaunchIntentForPackage(@NonNull String packageName){
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }
    public void startActivity(Intent intent){
        context.startActivity(intent);
    }
    public String getPackageName(){
        return context.getPackageName();
    }
    public String getExternalStorageDirectory(){
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * MEDIA_BAD_REMOVAL 在没有挂载前存储媒体已经被移除。
     * MEDIA_CHECKING 正在检查存储媒体。
     * MEDIA_MOUNTED 存储媒体已经挂载，并且挂载点可读/写。
     * MEDIA_MOUNTED_READ_ONLY 存储媒体已经挂载，挂载点只读。
     * MEDIA_NOFS 存储媒体是空白或是不支持的文件系统。
     * MEDIA_REMOVED 存储媒体被移除。
     * MEDIA_SHARED 存储媒体正在通过USB共享。
     * MEDIA_UNMOUNTABLE 存储媒体无法挂载。
     * MEDIA_UNMOUNTED 存储媒体没有挂载。
     * @return
     */
    public String getExternalStorageState(){
        return Environment.getExternalStorageState();
    }

    public  String getSystemModel() {
        return android.os.Build.MODEL;
    }
    public  String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }


    /**
     * 模拟器可能用不了，需要真机
     * @param text
     */
    public void setClipBoard(String text){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //创建ClipData对象
        ClipData clipData = ClipData.newPlainText("uiautomator_text", text);
        //添加ClipData对象到剪切板中
        clipboardManager.setPrimaryClip(clipData);
    }


    public static final String SHOT_PATH = "/UIAUTOMATOR_SCREEN_SHOT";
    /**
     * 截图失败返回false
     * @return
     */
    public boolean screenShot(UiDevice device, String fileName){
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

    /**
     * png pic
     * @param device
     * @return
     */
    public byte[] screenShot(UiDevice device,  float scale, int quality){
        Bitmap screenshot = getUiAutomation().takeScreenshot();
        if (screenshot == null) {
            return null;
        }
        BufferedOutputStream bos = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bos = new BufferedOutputStream(byteArrayOutputStream);
            if (bos != null) {
                screenshot.compress(Bitmap.CompressFormat.PNG, quality, bos);
                bos.flush();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException ioe) {
            UIAutomatorLogger.e( "failed to save screen shot to file"+ioe.getMessage());
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException ioe) {
                    // Ignore
                }
            }
            screenshot.recycle();
        }
    }
    /**
     * png pic
     * @param device
     * @return
     */
    public byte[] screenShot(UiDevice device){
        return screenShot(device, 1.0f, 90);
    }
    /**
     * png pic
     * @param device
     * @return
     */
    public byte[] screenShot(UiDevice device, UiObject2 uiObject,  float scale, int quality){
        Bitmap screenshot = getUiAutomation().takeScreenshot();
        if (screenshot == null) {
            return null;
        }
        if (uiObject == null) {
            return null;
        }
        Rect rect = uiObject.getVisibleBounds();
        int wi = screenshot.getWidth();
        Bitmap real = Bitmap.createBitmap(screenshot, rect.left, rect.top, rect.width(), rect.height());
        BufferedOutputStream bos = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bos = new BufferedOutputStream(byteArrayOutputStream);
            if (bos != null) {
                real.compress(Bitmap.CompressFormat.PNG, quality, bos);
                bos.flush();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException ioe) {
            UIAutomatorLogger.e( "failed to save screen shot to file"+ioe.getMessage());
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException ioe) {
                    // Ignore
                }
            }
            real.recycle();
            screenshot.recycle();
        }
    }
    /**
     * png pic
     * @param device
     * @return
     */
    public byte[] screenShot(UiDevice device, UiObject2 uiObject){
       return screenShot(device, uiObject, 1.0f, 90);
    }

    public String uuid(){
        return Build.FINGERPRINT;
    }
}
