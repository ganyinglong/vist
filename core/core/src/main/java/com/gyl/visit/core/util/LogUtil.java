package com.gyl.visit.core.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 *
 * @author ganyinglong
 */
public class LogUtil {

    private static Logger logger() {
        StackTraceElement caller = findCaller();
        if (null == caller) {
            return LoggerFactory.getLogger(LogUtil.class);
        }
        String[] names = caller.getClassName().split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < names.length - 1; i++) {
            stringBuilder.append(names[i].charAt(0)).append(".");
        }
        stringBuilder.append(names[names.length - 1]);
        return LoggerFactory.getLogger(stringBuilder.toString() + "." + caller.getMethodName() + "() [Line:" + caller.getLineNumber() + "]");
    }

    private static StackTraceElement findCaller() {
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        if (null == callStack) {
            return null;
        }
        StackTraceElement caller = null;
        String logClassName = LogUtil.class.getName();
        boolean isEachLogClass = false;

        for (StackTraceElement stackTraceElement : callStack) {
            if (logClassName.equals(stackTraceElement.getClassName())) {
                isEachLogClass = true;
            }
            if (isEachLogClass && !logClassName.equals(stackTraceElement.getClassName())) {
                isEachLogClass = false;
                caller = stackTraceElement;
                break;
            }
        }
        return caller;
    }

    public static void info(String msg) {
        logger().info(msg);
    }

    /**
     * info级别日志带参数
     *
     * @param msg    消息信息
     * @param params 参数
     */
    public static void info(String msg, Object... params) {
        logger().info(msg, params);
    }


    private static String buildLogMsg(StringBuilder msgBuilder, Object[] params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                Object o = params[i];
                String os = JsonUtil.toJsonWithDateFormat(o, DateUtil.YYYY_MM_DD_HH_MM_SS);
                msgBuilder.append("参数").append(i + 1).append(":");
                msgBuilder.append(os).append(";");
            }
        }
        return msgBuilder.toString();
    }

    public static void warn(String msg) {
        logger().warn(msg);
    }

    public static void warn(String msg, Object... params) {
        logger().warn(msg, params);
    }

    public static void error(String msg, Throwable throwable) {
        logger().error(msg, throwable);
    }

    public static void error(String msg) {
        logger().error(msg);
    }

    public static void error(String msg, Object... params) {
        logger().error(msg, params);
    }

    public static void error(String msg, Throwable throwable, Object... params) {
        StringBuilder lgMsg = new StringBuilder(msg);
        String s = buildLogMsg(lgMsg, params);
        logger().error(s, throwable);
    }

    public static String getthreadid() {
        return String.valueOf(Thread.currentThread().getId());
    }

}
