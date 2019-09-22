package com.gyl.visit.core.util;

import java.util.Collection;

public class CollectionUtil {
    public static boolean isEmpty(Collection<?> clc) {
        return null == clc || clc.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> clc) {
        return clc != null && clc.size() > 0;
    }

}
