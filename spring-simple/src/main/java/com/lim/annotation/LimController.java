package com.lim.annotation;

import java.lang.annotation.*;

/**
 * Controller
 * @author qinhao
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimController {
    String value() default "";
}
