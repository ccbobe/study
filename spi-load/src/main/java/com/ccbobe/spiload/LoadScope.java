package com.ccbobe.spiload;

import lombok.NonNull;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoadScope {

    String name() default "";

    String version() default "1.0.0";

    int order() default Integer.MIN_VALUE;
}
