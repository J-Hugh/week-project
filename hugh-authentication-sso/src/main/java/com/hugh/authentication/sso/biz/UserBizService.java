package com.hugh.authentication.sso.biz;

import com.hugh.authentication.core.commons.annotation.EasyValid;
import com.hugh.authentication.core.infrastructure.mysql.user.domain.SysUser;
import com.hugh.authentication.core.infrastructure.mysql.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/26 16:02
 * @Description:
 */
@Service
@EasyValid
@Slf4j
public class UserBizService {

    @Autowired
    private SysUserService sysUserService;

    public String userLogin(User user,@NotBlank(message = "账号不能为空2") String userAccount, @NotBlank(message = "密码不能为空2") String password, String remoteIP, String userAgent) {



        String userString = "";
        return null;
    }
}
