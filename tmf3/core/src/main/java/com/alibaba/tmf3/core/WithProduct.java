package com.alibaba.tmf3.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: kuhe
 * Date: 2017/7/7
 * Time: PM6:59
 */


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //can use in method only.
public @interface WithProduct {

    String value() default "alibaba";

}
