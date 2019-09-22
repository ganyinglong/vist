package com.gyl.visit.core.util;

import java.util.Map;

/**
 * @author ganyinglong
 */
public class MapUtil {
    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return map != null && !map.isEmpty();
    }
}
