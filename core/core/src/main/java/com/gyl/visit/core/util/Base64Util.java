package com.gyl.visit.core.util;


import java.util.Base64;

public class Base64Util {
    /**
     * BASE64解码
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(String content) {
        return Base64.getDecoder().decode(content);
    }

    public static String decryptString(String content) throws Exception {
        return new String(decrypt(content), "UTF-8");
    }

    public static String encryptString(String content) throws Exception {
        return encrypt(content.getBytes("UTF-8"));
    }

    /**
     * BASE64编码
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static String encrypt(byte[] content) {
        byte[] encode = Base64.getEncoder().encode(content);
        return new String(encode);
    }
}
