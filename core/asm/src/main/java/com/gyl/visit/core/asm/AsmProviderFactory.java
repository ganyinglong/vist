package com.gyl.visit.core.asm;

import java.util.HashMap;
import java.util.Map;

public class AsmProviderFactory {
    private static Map<String, AsmProvider> cacheMap = new HashMap<>();

    public static AsmProvider getProvider(Class c) {
        String name = c.getName();
        AsmProvider asmProvider = cacheMap.get(name);
        if (asmProvider == null) {
            asmProvider = AsmProvider.getInstance(c);
            cacheMap.put(name, asmProvider);
        }
        return asmProvider;
    }
}
