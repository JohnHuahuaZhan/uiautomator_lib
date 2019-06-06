package com.example.uiautomator.service;

import com.example.uiautomator.R;
import com.uiautomator.lib.support.service.BaseService;

public class CommonService extends BaseService {

    public String packageName = getContext().getStringResources(R.string.packageName);
    public String mainActivityName = getContext().getStringResources(R.string.mainActivityName);
    public long pollingEvery = Long.valueOf(getContext().getStringResources(R.string.pollingEvery));
    public long find_timeout = Long.valueOf(getContext().getStringResources(R.string.match_timeout));
    public CommonService(String tag, String memo) {
        super(tag, memo);
    }
    public CommonService() {

    }
}
