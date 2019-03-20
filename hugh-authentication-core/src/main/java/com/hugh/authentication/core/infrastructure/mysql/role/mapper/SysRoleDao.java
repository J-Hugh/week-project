package com.hugh.authentication.core.infrastructure.mysql.role.mapper;

import com.hugh.authentication.core.infrastructure.mysql.role.domain.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
public interface SysRoleDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<SysRole> searchByPage(Map<String, Object> searchParams);
	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<SysRole> search(Map<String, Object> searchParams);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	List<SysRole> findAll();
	
	/**
	 * 通过 iD 获取一条记录
	 * @param id 主键
	 * @return
	 */
	SysRole findById(String id);
	
	/**
	 * 保存一条记录
	 * @param sysRole
	 */
	void save(SysRole sysRole);
	
	/**
	 * 通过sysRole删除一条记录
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 修改记录
	 * @param sysRole
	 */
	void update(SysRole sysRole);
}