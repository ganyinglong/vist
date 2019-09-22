package com.gyl.visit.core.util;

public class HexUtil {
    private static final int[] DEC = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15};
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * @param bytes
     * @return
     * @description 将二进制转换成16进制
     */
    public static String byte2hex(byte[] bytes) {
        if (null == bytes) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bytes.length << 1);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(HEX[(bytes[i] & 240) >> 4]).append(HEX[bytes[i] & 15]);
        }
        return sb.toString();
    }

    /**
     * @param input
     * @return
     * @description 将16进制转换为二进制
     */
    public static byte[] hexToByte(String input) {
        if (input == null) {
            return null;
        }
        if ((input.length() & 1) == 1) {
            throw new IllegalArgumentException("hexUtils.fromHex.oddDigits");
        }
        byte[] bytes = input.getBytes();
        byte[] b2 = new byte[bytes.length >> 2];
        for (int i = 0; i < bytes.length; i += 2) {
            String item = new String(bytes, i, 2);
            b2[i >> 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;

    }

    public static int getDec(int index) {
        try {
            return DEC[index - 48];
        } catch (ArrayIndexOutOfBoundsException var2) {
            return -1;
        }
    }

}
