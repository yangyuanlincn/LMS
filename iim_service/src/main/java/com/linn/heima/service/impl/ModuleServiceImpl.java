package com.linn.heima.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.linn.heima.dao.BaseDao;
import com.linn.heima.domain.Module;
import com.linn.heima.service.ModuleService;
import com.linn.heima.utils.Page;

public class ModuleServiceImpl implements ModuleService{
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Module> find(String hql, Class<Module> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Module get(Class<Module> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Module> findPage(String hql, Page<Module> page, Class<Module> entityClass, Object[] params) {
		return baseDao.findPage(hql, page,entityClass, params);
	}

	public void saveOrUpdate(Module entity) {
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Module> entitys) {
		
	}

	public void deleteById(Class<Module> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<Module> entityClass, Serializable[] ids) {
		for(Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}
	

}
