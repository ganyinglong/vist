package com.gyl.visit.core.asm;

import com.esotericsoftware.reflectasm.MethodAccess;

public class AsmMethod<T> {
    private String name;
    private int index;
    private T returnType;

    public AsmMethod(String name, int index, T returnType) {
        this.name = name;
        this.index = index;
        this.returnType = returnType;
    }

    public <T> T invoke(MethodAccess methodAccess, Object source, Object... params) {
        return (T) methodAccess.invoke(source, index, params);
    }

    public String getName() {
        return name;
    }
}
