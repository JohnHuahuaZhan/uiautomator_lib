package com.uiautomator.lib.support.util.common.secret;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static byte[] MD5Encode(byte[] origin) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(origin);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {

        }
        return null;
    }
    public static String MD5EncodeString(byte[] origin) {
        return byteArrayToHexString(MD5Encode(origin));
    }
    public static boolean compare(byte[] origin, byte[] other) {
        return MessageDigest.isEqual(origin, other);
    }
    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
