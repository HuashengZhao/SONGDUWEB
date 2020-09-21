package com.example.EAS.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * api 接口控制
 * @author wusiwei
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiControl {
    
    String apiCode();
    
    String apiName();
    
    String limitLevel() default "default";
    
    String remark() default "";
    
    int version() default 1;
}
