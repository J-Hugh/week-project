package com.hugh.authentication.core.infrastructure.mysql.user.service;

import com.hugh.authentication.core.infrastructure.mysql.user.domain.SysUser;
import com.hugh.authentication.core.infrastructure.mysql.user.mapper.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "users")
public class SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<SysUser> searchByPage(Map<String,Object> searchParams)  {
		return sysUserDao.searchByPage(searchParams);
	}

	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<SysUser> search(Map<String,Object> searchParams)   {
		return sysUserDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<SysUser> findAll()  {
		return sysUserDao.findAll();
	}
	
	/**
	 * 通过获取一条记录
	 * @param  id 主键
	 * @return
	 */
	@Cacheable(key = "#p0")
	public SysUser findById(String id)  {
		return sysUserDao.findById(id);
	}

	/**
	 * 保存一条记录
	 * @param sysUser
	 */
	public void save(SysUser sysUser)  {
		sysUserDao.save(sysUser);
	}

	/**
	 * 通过  删除一条记录
	 * @param id 主键
	 */
	public void delete(String id)  {
		sysUserDao.delete(id);

	}
	
	/**
	 * 修改记录
	 * @param sysUser
	 */
	public void update(SysUser sysUser)  {
		sysUserDao.update(sysUser);
	}


    /**
     * 根据账号密码查询
     * @param userAccount
     * @param password
     * @return
     */
    @Cacheable(key = "#userAccount+'_'+#password")
    public SysUser findByPwd(String userAccount, String password) {
		SysUser byPwd = sysUserDao.findByPwd(userAccount, password);
		return byPwd;
    }

}
