package com.linn.heima.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.linn.heima.dao.BaseDao;
import com.linn.heima.domain.User;
import com.linn.heima.service.UserService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.UtilFuns;

public class UserServiceImpl implements UserService{
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<User> find(String hql, Class<User> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public User get(Class<User> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
		return baseDao.findPage(hql, page,entityClass, params);
	}

	public void saveOrUpdate(User entity) {
		if(UtilFuns.isEmpty(entity.getId())) {
			String uuid = UUID.randomUUID().toString();
			entity.setId(uuid);
			entity.getUserInfo().setId(uuid);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<User> entitys) {
		
	}

	public void deleteById(Class<User> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
		
	}

	public void delete(Class<User> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}
	

}
