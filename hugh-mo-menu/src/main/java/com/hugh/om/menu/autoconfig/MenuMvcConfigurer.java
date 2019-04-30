package com.hugh.om.menu.autoconfig;

import com.hugh.om.menu.interceptor.ViewResolverInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MenuMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private ViewResolverInterceptor viewResolverInterceptor;

    @Bean
    public ViewResolverInterceptor getViewResolverInterceptor() {
        return new ViewResolverInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(viewResolverInterceptor).addPathPatterns("/**");
    }

}
