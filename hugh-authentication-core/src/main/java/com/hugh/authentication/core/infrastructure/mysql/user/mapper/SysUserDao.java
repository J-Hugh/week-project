package com.hugh.authentication.core.infrastructure.mysql.user.mapper;

import com.hugh.authentication.core.infrastructure.mysql.user.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface SysUserDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<SysUser> searchByPage(Map<String, Object> searchParams);
	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<SysUser> search(Map<String, Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<SysUser> findAll();
	
	/**
	 * 通过 iD 获取一条记录
	 * @param iD 主键
	 * @return
	 */
	SysUser findById(String iD);


	/**
	 * 根据账号密码查询
	 * @param userAccount
	 * @param userPwd
	 * @return
	 */
	SysUser findByPwd(@Param("userAccount") String userAccount, @Param("userPwd") String userPwd);

	/**
	 * 保存一条记录
	 * @param sysUser
	 */
	void save(SysUser sysUser);
	
	/**
	 * 通过sysUser删除一条记录
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 修改记录
	 * @param sysUser
	 */
	void update(SysUser sysUser);
}