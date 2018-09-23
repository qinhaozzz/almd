package com.lim.annotation;

import java.lang.annotation.*;

/**
 * RequestParam
 * @author qinhao
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimRequestParam {
    String value() default "";
}
