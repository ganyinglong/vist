package com.gyl.visit.core.util;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {
    private static ThreadLocal<String> UID_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static String getCid() {
        return UID_THREAD_LOCAL.get();
    }

    public static void setUidThreadLocal(String cid) {
        UID_THREAD_LOCAL.set(cid);
    }

    public static void remove() {
        UID_THREAD_LOCAL.remove();
    }

}
