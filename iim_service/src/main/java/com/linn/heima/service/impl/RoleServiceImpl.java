package com.linn.heima.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.linn.heima.dao.BaseDao;
import com.linn.heima.domain.Role;
import com.linn.heima.service.RoleService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.UtilFuns;

public class RoleServiceImpl implements RoleService{
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Role> find(String hql, Class<Role> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Role get(Class<Role> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Role> findPage(String hql, Page<Role> page, Class<Role> entityClass, Object[] params) {
		return baseDao.findPage(hql, page,entityClass, params);
	}

	public void saveOrUpdate(Role entity) {
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Role> entitys) {
		
	}

	public void deleteById(Class<Role> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
		
	}

	public void delete(Class<Role> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}
	

}
