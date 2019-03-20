package com.hugh.authentication.sso.autoconfig;

import com.hugh.authentication.core.commons.exception.BusinessException;
import com.hugh.authentication.sso.controller.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/28 09:45
 * @Description: 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest request,Exception ex) {
        //业务异常不打印异常栈
        if (ex instanceof BusinessException) {
            log.warn("业务异常：{}",ex.getMessage());
        } else {
            log.error("系统异常：", ex);
        }
        if (isAjaxRequest(request)) {
            return new HttpResponse(500, ex.getMessage(), Boolean.FALSE, null);
        } else {
            ModelAndView modelAndView = new ModelAndView();

            modelAndView.addObject("time", LocalDateTime.now().toString());
            modelAndView.addObject("url", request.getRequestURL().toString());
            modelAndView.addObject("error", ex.getMessage());
            modelAndView.addObject("statusCode", getHttpStatus(request).value());
            modelAndView.addObject("reasonPhrase", getHttpStatus(request).getReasonPhrase());
            modelAndView.addObject("stackTrace", getStackTraceInfo(ex));
            modelAndView.setViewName("error");

            return modelAndView;
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    public HttpStatus getHttpStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
        try {
            return statusCode != null ? HttpStatus.valueOf(statusCode) : HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public String getStackTraceInfo(Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        return stackTrace.toString();
    }

}