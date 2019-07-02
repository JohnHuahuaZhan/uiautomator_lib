package com.uiautomator.lib.support.network;


public class HttpResponseHelper {
    public static final String INVALID_TAG_NAME = "HttpResponseHelper_invalid_tag_name";

    public static void updateForm(MyResponse response, byte[] newByte) {
        response.setBytes(newByte);
    }

}
