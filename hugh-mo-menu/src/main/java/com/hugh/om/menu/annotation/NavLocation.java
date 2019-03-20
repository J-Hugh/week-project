package com.hugh.om.menu.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NavLocation {

    /**
     * 左侧菜单导航选中Code
     * @return
     */
    String selected();

    /**
     * 是否有左侧菜单，默认有
     * @return
     */
    boolean hasLeftMenu() default true;
}
