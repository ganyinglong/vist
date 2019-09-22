package com.gyl.visit.core.util;

public class StringUtils {
    public static boolean isEmpty(String arg) {
        return arg == null || arg.length() == 0;
    }

    public static boolean isNotEmpty(String arg) {
        return !isEmpty(arg);
    }

    public static boolean isEquals(String arg1, String arg2) {
        if (arg1 != null) {
            return arg1.equals(arg2);
        }
        if (null == arg1 && null == arg2) {
            return true;
        }
        return false;
    }

    public static String toString(Object o) {
        if (o == null) {
            return null;
        } else {
            return o.toString();
        }
    }

    /**
     * 将指定位置的字符转为大写
     *
     * @param arg
     * @param indexs
     * @return
     */
    public static String toUpperCase(String arg, int... indexs) {
        if (isEmpty(arg) || null == indexs) {
            return arg;
        }
        byte[] bytes = arg.getBytes();
        for (int i = 0; i < indexs.length; i++) {
            int index = indexs[i];
            if (index < bytes.length && bytes[index] >= 97 && bytes[index] <= 122) {
                bytes[index] = (byte) (bytes[index] - 32);
            }
        }
        return new String(bytes);
    }
}
