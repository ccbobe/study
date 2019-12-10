package com.bobe.leader.core;

import java.lang.annotation.*;

/**
 * @Author:ccbobe
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface Count {
    public String count() default "0";
}
