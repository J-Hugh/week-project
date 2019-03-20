package com.hugh.authentication.core.infrastructure.mysql.role.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugh.authentication.core.infrastructure.mysql.role.mapper.SysRoleDao;
import com.hugh.authentication.core.infrastructure.mysql.role.domain.SysRole;

@Service
public class SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<SysRole> searchByPage(Map<String,Object> searchParams) throws Exception {
		return (List<SysRole>) sysRoleDao.searchByPage(searchParams);
	}

	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<SysRole> search(Map<String,Object> searchParams) throws Exception  {
		return (List<SysRole>) sysRoleDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<SysRole> findAll() throws Exception {
		return (List<SysRole>) sysRoleDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  id 主键
	 * @return
	 */
	public SysRole findById(String id) throws Exception {
		SysRole sysRole = sysRoleDao.findById(id);
		if(sysRole == null){
			sysRole = new SysRole();
		}
		return sysRole;
	}

	/**
	 * 保存一条记录
	 * @param sysRole
	 */
	public void save(SysRole sysRole) throws Exception {
				sysRoleDao.save(sysRole);
	}

	/**
	 * 通过  删除一条记录
	 * @param id 主键
	 */
	public void delete(String id) throws Exception {
		sysRoleDao.delete(id);

	}
	
	/**
	 * 修改记录
	 * @param sysRole
	 */
	public void update(SysRole sysRole) throws Exception {
		sysRoleDao.update(sysRole);
	}
}
