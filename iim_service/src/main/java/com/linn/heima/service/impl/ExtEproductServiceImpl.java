package com.linn.heima.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.linn.heima.dao.BaseDao;
import com.linn.heima.domain.ExtEproduct;
import com.linn.heima.service.ExtEproductService;
import com.linn.heima.utils.Page;

public class ExtEproductServiceImpl implements ExtEproductService{
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ExtEproduct> find(String hql, Class<ExtEproduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public ExtEproduct get(Class<ExtEproduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<ExtEproduct> findPage(String hql, Page<ExtEproduct> page, Class<ExtEproduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page,entityClass, params);
	}

	public void saveOrUpdate(ExtEproduct entity) {
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ExtEproduct> entitys) {
		
	}

	public void deleteById(Class<ExtEproduct> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
		
	}

	public void delete(Class<ExtEproduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}

}
