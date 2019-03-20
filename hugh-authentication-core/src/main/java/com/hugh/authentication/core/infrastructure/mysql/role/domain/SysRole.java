package com.hugh.authentication.core.infrastructure.mysql.role.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    
    /**
      * 主键
      **/
	private String id;
    /**
      * 角色编码
      **/
	private String roleCode;
    /**
      * 角色名称
      **/
	private String roleName;
    /**
      * 角色类型 1-普角色 2-部门角色
      **/
	private Integer roleType;
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