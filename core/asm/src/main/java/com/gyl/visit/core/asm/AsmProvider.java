package com.gyl.visit.core.asm;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Method;
import java.util.Objects;

public class AsmProvider {
    private MethodAccess methodAccess;
    private AsmMethod[] asmMethods;

    private AsmProvider(MethodAccess methodAccess, AsmMethod[] asmMethods) {
        this.methodAccess = methodAccess;
        this.asmMethods = asmMethods;
    }


    public static AsmProvider getInstance(Class c) {
        MethodAccess methodAccess = MethodAccess.get(c);
        Method[] methods = c.getDeclaredMethods();
        AsmMethod[] asmMethods = new AsmMethod[methods.length];
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String name = method.getName();
            int index = methodAccess.getIndex(name);
            Class<?> returnType = method.getReturnType();
            asmMethods[i] = new AsmMethod(name, index, returnType);
        }
        return new AsmProvider(methodAccess, asmMethods);
    }

    public <T> T invokeMethod(String methodName) {
        for (AsmMethod asmMethod : asmMethods) {
            String name = asmMethod.getName();
            if (Objects.equals(methodName, name)) {

            }
        }
        return null;
    }


    public AsmMethod[] getAsmMethods() {
        return asmMethods;
    }

    public MethodAccess getMethodAccess() {
        return methodAccess;
    }
}
