package xyz.qumn.lb.management.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xyz.qumn.lb.management.api.enums.CarStatusEnum;

import java.lang.reflect.Field;

@RunWith(JUnit4.class)
public class test {
    @Test
    public void test(){
        CarStatusEnum[] enumConstants = CarStatusEnum.class.getEnumConstants();
        for (CarStatusEnum enumConstant : enumConstants) {
            System.out.println(enumConstant.name());
        }
    }
}
