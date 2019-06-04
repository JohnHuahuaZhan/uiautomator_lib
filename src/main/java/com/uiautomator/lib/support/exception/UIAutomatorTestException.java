/**
 * @author 菜花
 * @date 20190524
 */
package com.uiautomator.lib.support.exception;

import java.util.HashMap;
import java.util.Map;

public class UIAutomatorTestException extends RuntimeException {

    public static String supportUrl = "https://developer.android.com/training/testing/espresso";
    private Map<String, String> extraInfo = new HashMap<>();
    public UIAutomatorTestException() {
        super();
    }

    public UIAutomatorTestException(String message) {
        super(message);
    }

    public UIAutomatorTestException(Throwable cause) {
        super(cause);
    }

    public UIAutomatorTestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getCause() instanceof UIAutomatorTestException
                ? super.getMessage() : createMessage(super.getMessage());
    }

    private String createMessage(String originalMessageString) {
        String supportMessage = getSupportUrl() == null ?
                "" : "For documentation on this error, please visit: " + getSupportUrl() + "\n";

        return (originalMessageString == null ? "" : originalMessageString + "\n")
                + supportMessage
                + getSystemInformation()
                + getAdditionalInformation();
    }

    public String getSystemInformation() {
        return String.format("System info:%s", System.getProperties().toString());
    }

    public String getSupportUrl() {
        return supportUrl;
    }




    public void addInfo(String key, String value) {
        extraInfo.put(key, value);
    }

    public String getAdditionalInformation() {
        String result = "";
        for (Map.Entry<String, String> entry : extraInfo.entrySet()) {
            if (entry.getValue() != null && entry.getValue().startsWith(entry.getKey())) {
                result += "\n" + entry.getValue();
            } else {
                result += "\n" + entry.getKey() + ": " + entry.getValue();
            }
        }
        return result;
    }
}
