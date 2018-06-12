package com.linn.heima.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.linn.heima.dao.BaseDao;
import com.linn.heima.domain.ExportProduct;
import com.linn.heima.service.ExportProductService;
import com.linn.heima.utils.Page;

public class ExportProductServiceImpl implements ExportProductService{
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ExportProduct> find(String hql, Class<ExportProduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public ExportProduct get(Class<ExportProduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<ExportProduct> findPage(String hql, Page<ExportProduct> page, Class<ExportProduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page,entityClass, params);
	}

	public void saveOrUpdate(ExportProduct entity) {
		
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ExportProduct> entitys) {
		
	}

	public void deleteById(Class<ExportProduct> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
		
	}

	public void delete(Class<ExportProduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}

	

}
