package com.gyl.visit.core.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityUtil {

    private static final String UTF_8 = "UTF-8";

    public static String encryptByAES(String content, String key) throws Exception {
        return HexUtil.byte2hex(encryptByte(content, key));
    }

    public static String decryptByAES(String content, String key) throws Exception {
        return new String(decryptByte(content, key), UTF_8);
    }

    public static byte[] decryptByte(String content, String key) throws Exception {
        if (content == null || key == null) {
            throw new IllegalArgumentException();
        }
        byte[] bytes = HexUtil.hexToByte(content);
        return disposalByAES(bytes, key, Cipher.DECRYPT_MODE);
    }

    public static byte[] encryptByte(String content, String key) throws Exception {
        if (content == null || key == null) {
            throw new IllegalArgumentException();
        }
        return disposalByAES(content.getBytes(UTF_8), key, Cipher.ENCRYPT_MODE);
    }

    private static byte[] disposalByAES(byte[] content, String key, int mode) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        String aes = "AES";
        KeyGenerator keyGenerator = KeyGenerator.getInstance(aes);
        SecureRandom srandom = SecureRandom.getInstance("SHA1PRNG");
        srandom.setSeed(key.getBytes(UTF_8));
        keyGenerator.init(128, srandom);
        SecretKeySpec keySpec = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), aes);
        Cipher cipher = Cipher.getInstance(aes);
        cipher.init(mode, keySpec);
        return cipher.doFinal(content);
    }

}
