package com.ccbobe;


import java.lang.annotation.*;

/**
 * @author ccbobe
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoutingClass {
    String name() default  "";
    String[] values() default  {};
    String version() default "1";
}
