package com.lim.annotation;

import java.lang.annotation.*;

/**
 * Autowired
 * @author qinhao
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimAutowired {
    String value() default "";
}
