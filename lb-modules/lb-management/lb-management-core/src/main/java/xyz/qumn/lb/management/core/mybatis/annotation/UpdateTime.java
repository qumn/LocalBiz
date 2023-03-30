package xyz.qumn.lb.management.core.mybatis.annotation;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.lang.annotation.*;

@TableField(fill = FieldFill.INSERT_UPDATE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Inherited
public @interface UpdateTime {
}
