package com.lim.annotation;

import java.lang.annotation.*;

/**
 * RequestMapping
 * @author qinhao
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimRequestMapping {
    String value() default "";
}
