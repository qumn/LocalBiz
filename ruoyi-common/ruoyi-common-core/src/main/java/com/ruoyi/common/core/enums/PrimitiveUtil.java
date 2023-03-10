package com.ruoyi.common.core.enums;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 包装类型转换
 **/
public final class PrimitiveUtil {

    private static final Map<Class<?>, Class<?>> PRIMITIVE_TYPE_TO_WRAPPER_MAP = new IdentityHashMap<>(8);

    static {
        PRIMITIVE_TYPE_TO_WRAPPER_MAP.put(boolean.class, Boolean.class);
        PRIMITIVE_TYPE_TO_WRAPPER_MAP.put(byte.class, Byte.class);
        PRIMITIVE_TYPE_TO_WRAPPER_MAP.put(char.class, Character.class);
        PRIMITIVE_TYPE_TO_WRAPPER_MAP.put(double.class, Double.class);
        PRIMITIVE_TYPE_TO_WRAPPER_MAP.put(float.class, Float.class);
        PRIMITIVE_TYPE_TO_WRAPPER_MAP.put(int.class, Integer.class);
        PRIMITIVE_TYPE_TO_WRAPPER_MAP.put(long.class, Long.class);
        PRIMITIVE_TYPE_TO_WRAPPER_MAP.put(short.class, Short.class);
    }

    public static Class<?> resolvePrimitiveIfNecessary(Class<?> clazz) {
        return (clazz.isPrimitive() && clazz != void.class ? PRIMITIVE_TYPE_TO_WRAPPER_MAP.get(clazz) : clazz);
    }

}