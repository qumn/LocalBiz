package com.ruoyi.common.core.enums;

import java.io.Serializable;

public interface BaseEnum<T extends Serializable> {

    /**
     * 枚举数据库存储值
     */
    T getValue();
}