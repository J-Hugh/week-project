package com.hugh.authentication.core.infrastructure.mysql.user.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author luoyulin1
 */
@Data
public class SysUser implements Serializable {
    
    /**
      * 
      **/
	private String id;
    /**
      * 用户编号
      **/
	private String userCode;
    /**
      * 用户账号
      **/
	private String userAccount;
    /**
      * 用户姓名
      **/
	private String realName;
    /**
      * 登录密码
      **/
	private String userPwd;
    /**
      * 部门编号
      **/
	private String deptCode;

    /**
     * 是否是测试账号 1是2不是
     */
    private Integer isTest;
    /**
      * 创建人
      **/
	private String creator;
    /**
      * 创建时间
      **/
	private Date createTime;
    /**
      * 更新人
      **/
	private String modifier;
    /**
      * 更新时间
      **/
	private Date modifyTime;


}