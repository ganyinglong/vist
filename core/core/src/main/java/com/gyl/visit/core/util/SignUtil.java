package com.gyl.visit.core.util;

public class SignUtil {
    public static String signature(String contant, String key) {
        String s = MD5Util.md5(contant + key);
        return s;
    }

    public static boolean valid(String signature, String contant, String key) {
        String md5 = MD5Util.md5(contant + key);
        return StringUtils.isEquals(signature, md5);
    }
}
