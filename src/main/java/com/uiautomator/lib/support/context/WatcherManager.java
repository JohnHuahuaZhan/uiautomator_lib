package com.uiautomator.lib.support.context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.Tracer;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiWatcher;

import com.uiautomator.lib.support.log.UIAutomatorLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WatcherManager {
    private static final WatcherManager ourInstance = new WatcherManager();

    public static WatcherManager getInstance() {
        return ourInstance;
    }




    private WatcherManager() {
        registerDefaultWatchers();
    }
    private UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    private boolean mInWatcherContext = false;
    private final HashMap<String, UiWatcher> mWatchers = new HashMap<String, UiWatcher>();
    private final List<String> mWatchersTriggers = new ArrayList<String>();
    public void registerWatcher(String name, UiWatcher watcher) {
        Tracer.trace(name, watcher);
        if (mInWatcherContext) {
            throw new IllegalStateException("Cannot register new watcher from within another");
        }
        mWatchers.put(name, watcher);
    }
    private void registerDefaultWatchers(){
        registerWatcher("COMMON_PERMISSION", new DefaultWatchers.CommonIdPermissionWatcher(device));
        registerWatcher("ALWAYS_ALLOW_PERMISSION", new DefaultWatchers.ClickTextWatcher(device, "始终允许"));
        registerWatcher("ALWAYS_ALLOW_PERMISSION_1", new DefaultWatchers.ClickTextWatcher(device, "总是允许"));
        registerWatcher("ALLOW_PERMISSION", new DefaultWatchers.ClickTextWatcher(device, "允许"));
    }

    public void removeWatcher(String name) {
        Tracer.trace(name);
        if (mInWatcherContext) {
            throw new IllegalStateException("Cannot remove a watcher from within another");
        }
        mWatchers.remove(name);
    }


    public void runWatchers() {
        Tracer.trace();
        if (mInWatcherContext) {
            return;
        }

        for (String watcherName : mWatchers.keySet()) {
            UiWatcher watcher = mWatchers.get(watcherName);
            if (watcher != null) {
                try {
                    mInWatcherContext = true;
                    if (watcher.checkForCondition()) {
                        setWatcherTriggered(watcherName);
                        return;//只要有触发的就返回，防止只要找不到就都跑一遍。
                    }
                } catch (Exception e) {
                    UIAutomatorLogger.d("Exceuting watcher: " + watcherName);
                } finally {
                    mInWatcherContext = false;
                }
            }
        }
    }


    public void resetWatcherTriggers() {
        Tracer.trace();
        mWatchersTriggers.clear();
    }


    public boolean hasWatcherTriggered(String watcherName) {
        Tracer.trace(watcherName);
        return mWatchersTriggers.contains(watcherName);
    }


    public boolean hasAnyWatcherTriggered() {
        Tracer.trace();
        return mWatchersTriggers.size() > 0;
    }


    private void setWatcherTriggered(String watcherName) {
        Tracer.trace(watcherName);
        if (!hasWatcherTriggered(watcherName)) {
            mWatchersTriggers.add(watcherName);
        }
    }

}
