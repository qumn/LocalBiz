package com.ruoyi.system.api.enums;

import com.ruoyi.common.core.enums.BaseEnum;
import com.ruoyi.common.core.enums.EnumValue;

public enum UserType implements BaseEnum {
    INTERNAL("INTERNAL"), // 系统内部用户
    MERCHANT("MERCHANT"), // 商家
    NORMAL("NORMAL") // 普通用户
    ;

    @EnumValue
    String value;

    UserType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
