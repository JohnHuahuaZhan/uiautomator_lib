package com.uiautomator.lib.support;




import java.util.Base64;

import com.google.common.io.Files;
import com.uiautomator.lib.support.network.util.HttpRequestUtil;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class
NetworkTest {

    @Test
    public void testGet(){
        try {
            byte[] content = HttpRequestUtil.get("https", "www.zhihu.com", "/api/v3/oauth/captcha","443", "utf-8", null);
            System.out.println(new String(content));
            byte[] image = Base64.getDecoder().decode(
                    "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"
            );
            Files.write(image, new File("d:/image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
