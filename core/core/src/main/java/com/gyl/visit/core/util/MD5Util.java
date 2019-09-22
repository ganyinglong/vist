package com.gyl.visit.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String md5(byte[] bytes) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(bytes);
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException:MD5");
        }
        return HexUtil.byte2hex(secretBytes);
    }


    public static String md5(String plainText) {
        return md5(plainText.getBytes());
    }

}
