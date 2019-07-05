package com.uiautomator.lib.support.process;

import com.uiautomator.lib.support.network.MyRequest;
import com.uiautomator.lib.support.network.MyResponse;

public interface IRequestDelegate {
    default MyResponse delegate(MyRequest request) {
        return null;
    }
}
