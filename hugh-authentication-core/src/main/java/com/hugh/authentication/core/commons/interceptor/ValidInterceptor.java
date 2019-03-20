package com.hugh.authentication.core.commons.interceptor;

import com.hugh.authentication.core.commons.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/27 09:42
 * @Description:
 */
@Slf4j
public class ValidInterceptor implements MethodInterceptor {

    private final SpringValidatorAdapter validator;

    public ValidInterceptor() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = new SpringValidatorAdapter(validatorFactory.getValidator());
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        //方法级别验证
        ExecutableValidator executableValidator = validator.forExecutables();
        Method targetMethod = invocation.getMethod();
        Object[] arguments = invocation.getArguments();
        Set<ConstraintViolation<Object>> constraintViolations = executableValidator.validateParameters(invocation.getThis(), targetMethod, arguments, new Class<?>[0]);

        //对象级别验证
        if (CollectionUtils.isEmpty(constraintViolations)) {
            for (Object argument : arguments) {
                constraintViolations = validator.validate(argument);
            }
        }

        if (!CollectionUtils.isEmpty(constraintViolations)) {
            //String errorMsg = constraintViolations.stream().map(cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage()).collect(Collectors.joining(", "));
            String errorMsg = constraintViolations.stream().map(cv -> cv == null ? "null" : cv.getMessage()).collect(Collectors.joining(", "));
            throw new BusinessException(errorMsg);
        }

        return invocation.proceed();
    }
}
