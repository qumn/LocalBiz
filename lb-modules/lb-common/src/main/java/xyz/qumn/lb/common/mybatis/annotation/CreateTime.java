package xyz.qumn.lb.common.mybatis.annotation;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Documented
@TableField(fill = FieldFill.INSERT)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Inherited
public @interface CreateTime {
    FieldFill value() default FieldFill.INSERT;
}
