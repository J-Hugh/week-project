package com.hugh.authentication.sso.sdk.autoconfig;

import com.hugh.authentication.sso.sdk.interceptor.SpringSSOInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SsoMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private SpringSSOInterceptor springSSOInterceptor;

    @Bean
    public SpringSSOInterceptor getViewResolverInterceptor() {
        return new SpringSSOInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(springSSOInterceptor).addPathPatterns("/**").excludePathPatterns("/sso/login").excludePathPatterns("/static/**").excludePathPatterns("/error/**");
    }

}
