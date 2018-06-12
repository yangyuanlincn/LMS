package com.linn.heima.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.linn.heima.dao.BaseDao;
import com.linn.heima.domain.Contract;
import com.linn.heima.service.ContractService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.UtilFuns;

public class ContractServiceImpl implements ContractService{
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Contract> find(String hql, Class<Contract> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Contract get(Class<Contract> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Contract> findPage(String hql, Page<Contract> page, Class<Contract> entityClass, Object[] params) {
		return baseDao.findPage(hql, page,entityClass, params);
	}

	public void saveOrUpdate(Contract entity) {
		if(UtilFuns.isEmpty(entity.getId())) {
			entity.setTotalAmount(0d);
			entity.setState(0);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Contract> entitys) {
		
	}

	public void deleteById(Class<Contract> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
		
	}

	public void delete(Class<Contract> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}

	//更新状态
	public void changeState(String[] ids, int state) {
		for (String id : ids) {
			Contract contract = baseDao.get(Contract.class, id);
			contract.setState(state);
			//持久化对象，下面语句可以省略
			baseDao.saveOrUpdate(contract);
		}
	}
	

}
