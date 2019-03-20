package com.hugh.authentication.core.commons.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/27 10:31
 * @Description:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyValid {

}
