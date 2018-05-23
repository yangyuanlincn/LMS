package com.linn.heima.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.linn.heima.dao.BaseDao;
import com.linn.heima.domain.Dept;
import com.linn.heima.service.DeptService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.UtilFuns;

public class DeptServiceImpl implements DeptService{
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Dept get(Class<Dept> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {
		return baseDao.findPage(hql, page,entityClass, params);
	}

	public void saveOrUpdate(Dept entity) {
		if(UtilFuns.isEmpty(entity.getId())) {
			entity.setState(1);//1:启用 0：停用
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Dept> entitys) {
		
	}

	public void deleteById(Class<Dept> entityClass, Serializable id) {
		
	}

	public void delete(Class<Dept> entityClass, Serializable[] ids) {
		
	}
	

}
