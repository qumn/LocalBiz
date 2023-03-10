package com.ruoyi.common.core.enums;

import java.lang.annotation.*;

/**
 * 支持普通枚举类字段, 只用在enum类的字段上
 * 当实体类的属性是普通枚举，且是其中一个字段，使用该注解来标注枚举类里的那个属性对应字段
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface EnumValue {
}