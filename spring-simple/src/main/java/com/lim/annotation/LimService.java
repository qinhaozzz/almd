package com.lim.annotation;

import java.lang.annotation.*;

/**
 * Service
 * @author qinhao
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimService {
    String value() default "";
}
