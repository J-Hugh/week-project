package com.hugh.om.menu.interceptor;

import com.hugh.om.menu.annotation.NavLocation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class ViewResolverInterceptor implements HandlerInterceptor {

    @Autowired
    private FreeMarkerProperties freeMarkerProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("traceId","12222");
        log.info("preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (null == modelAndView) {
            return;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NavLocation navLocation = handlerMethod.getMethodAnnotation(NavLocation.class);
        if (null != navLocation && navLocation.hasLeftMenu()) {
            String targetView = modelAndView.getViewName();
            modelAndView.setViewName("/layout");
            Map<String, Object> model = modelAndView.getModel();
            model.put("content", targetView + freeMarkerProperties.getSuffix());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
