package com.uiautomator.lib.support.context;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.test.core.app.ApplicationProvider;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class TestContext {
    private static final TestContext ourInstance = new TestContext();
    private ToastAccessibilityEventListener toastAccessibilityEventListener = new ToastAccessibilityEventListener();
    public static TestContext getInstance() {
        return ourInstance;
    }
    Context context = ApplicationProvider.getApplicationContext();
    private TestContext() {
        getInstrumentation().getUiAutomation().setOnAccessibilityEventListener(toastAccessibilityEventListener);
    }
    public String getToastMessage(){
        return toastAccessibilityEventListener.getToastMessage();
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
}
