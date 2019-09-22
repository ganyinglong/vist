package com.gyl.visit.core.util;

/**
 * @author ganyinglong
 */
public class NumberUtil {

    public static boolean percententTrue(double percentent) {
        double random = Math.random();
        return random <= percentent;
    }

    public static int percentent(double... ps) {
        double count = 0.0;
        if (ps == null) {
            return 0;
        }
        for (double p : ps) {
            count += p;
        }
        if (count != 1.0) {
            throw new IllegalArgumentException("各概率之和必须等于1");
        }
        double random = Math.random();
        double boundary = 0.0;
        for (int i = 0; i < ps.length; i++) {
            double next = boundary + ps[i];
            if (random < next && random > boundary) {
                return i;
            }
            boundary = next;
        }
        throw new IllegalArgumentException("各概率之和必须等于1");
    }
}