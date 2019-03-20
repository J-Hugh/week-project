package com.hugh.authentication.core.autoconfig;

import com.hugh.authentication.core.commons.annotation.EasyValid;
import com.hugh.authentication.core.commons.interceptor.ValidInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.validation.beanvalidation.BeanValidationPostProcessor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/26 17:05
 * @Description: aop相关配置
 */
@Configuration
public class PointcutConfigurer {

    @Bean
    public DefaultPointcutAdvisor easyValidAnnotationClassPointCut() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        //配置优先级
        advisor.setOrder(Ordered.HIGHEST_PRECEDENCE);
        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(EasyValid.class,true);
        ValidInterceptor interceptor = new ValidInterceptor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}
