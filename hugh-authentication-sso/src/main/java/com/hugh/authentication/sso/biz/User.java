package com.hugh.authentication.sso.biz;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/26 16:59
 * @Description:
 */
@Data
public class User implements Serializable {

    @NotBlank(message = "账号不能为空1")
    private String userAccount;

    @NotBlank(message = "密码不能为空1")
    private String password;

}
