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
import android.telephony.TelephonyManager;
import android.util.Log;

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
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.TELEPHONY_SERVICE;
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








    protected UiDevice device = UiDevice.getInstance(getInstrumentation());
    public UiDevice getDevice() {
        return device;
    }

    private static final TestContext ourInstance = new TestContext();
    private ToastAccessibilityEventListener toastAccessibilityEventListener = new ToastAccessibilityEventListener();
    public static TestContext getInstance() {
        return ourInstance;
    }
    Context context = ApplicationProvider.getApplicationContext();
    Context insContext = InstrumentationRegistry.getInstrumentation().getContext();
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
        insContext.startActivity(intent);
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
     * jpeg pic
     * @return
     */
    public byte[] screenShot(int quality){
        Bitmap screenshot = getUiAutomation().takeScreenshot();
        if (screenshot == null) {
            return null;
        }
        BufferedOutputStream bos = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bos = new BufferedOutputStream(byteArrayOutputStream);
            if (bos != null) {
                screenshot.compress(Bitmap.CompressFormat.JPEG, quality, bos);
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
     * @return
     */
    public byte[] screenShot(){
        return screenShot(100);
    }
    /**
     * JPEG pic
     * @return
     */
    public byte[] screenShot( UiObject2 uiObject, int quality){
        Bitmap screenshot = getUiAutomation().takeScreenshot();
        if (screenshot == null) {
            return null;
        }
        if (uiObject == null) {
            return null;
        }
        Rect rect = uiObject.getVisibleBounds();
        Bitmap real = Bitmap.createBitmap(screenshot, rect.left, rect.top, rect.width(), rect.height());
        BufferedOutputStream bos = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bos = new BufferedOutputStream(byteArrayOutputStream);
            if (bos != null) {
                real.compress(Bitmap.CompressFormat.JPEG, quality, bos);
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
     * @return
     */
    public byte[] screenShot(UiObject2 uiObject){
       return screenShot( uiObject, 100);
    }

    public String uuid1(){
        return Build.FINGERPRINT;
    }
    public String uuid(){
        return String.format("%s-%s-%s",getSystemModel(), sn(), getSystemVersion());
    }

    /**
     * SN码是Serial Number的缩写，有时也叫SerialNo，也就是产品序列号，产品序列是为了验证“产品的合法身份”而引入的一个概念，
     * 它是用来保障用户的正版权益，享受合法服务的；一套正版的产品只对应一组产品序列号。别称：机器码、认证码、注册申请码等。
     * SN码就是软件开发商给软件的一个识别码，和人的身份证号码类似，其作用主要是为了防止自己的软件被用户盗用。用户要使用其
     * 软件就必须知道序列号。
     * MIUI输入*#*#64663#*#*可查询
     * @return
     */
    public String sn(){
        String serial = "";
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {//9.0+
                serial = Build.getSerial();
            } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {//8.0+
                serial = Build.SERIAL;
            } else {//8.0-
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class);
                serial = (String) get.invoke(c, "ro.serialno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("e", "读取设备序列号异常：" + e.toString());
        }
        return serial;
    }

    /**
     *国际移动设备识别码（International Mobile Equipment Identity，IMEI），即通常所说的手机序列号、手机“串号”，
     * 用于在移动电话网络中识别每一部独立的手机等移动通信设备，相当于移动电话的身份证
     * @return
     */
    public String imei(){
        TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }
}
