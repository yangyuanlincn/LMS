package com.linn.heima.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.linn.heima.dao.BaseDao;
import com.linn.heima.domain.Contract;
import com.linn.heima.domain.ExtCproduct;
import com.linn.heima.service.ExtCproductService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.UtilFuns;

public class ExtCproductServiceImpl implements ExtCproductService{
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ExtCproduct> find(String hql, Class<ExtCproduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public ExtCproduct get(Class<ExtCproduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<ExtCproduct> findPage(String hql, Page<ExtCproduct> page, Class<ExtCproduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page,entityClass, params);
	}

	public void saveOrUpdate(ExtCproduct entity) {
		double amount = 0d;
		//如果是添加
		if(UtilFuns.isEmpty(entity.getId())) {
			if(UtilFuns.isNotEmpty(entity.getPrice()) && UtilFuns.isNotEmpty(entity.getCnumber())) {
				amount = entity.getPrice()*entity.getCnumber();
			}
			entity.setAmount(amount);
			Contract contract = baseDao.get(Contract.class, entity.getContractProduct().getContract().getId());
			contract.setTotalAmount(contract.getTotalAmount()+amount);
		//如果是更新
		}else {
			//原来的总价
			double old = entity.getAmount();
			if(UtilFuns.isNotEmpty(entity.getPrice()) && UtilFuns.isNotEmpty(entity.getCnumber())) {
				amount = entity.getPrice()*entity.getCnumber();
			}
			entity.setAmount(amount);
			//持久化对象，save可以省略
			Contract contract = baseDao.get(Contract.class, entity.getContractProduct().getContract().getId());
			contract.setTotalAmount(contract.getTotalAmount()+amount-old);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ExtCproduct> entitys) {
		
	}

	public void deleteById(Class<ExtCproduct> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
		
	}

	public void delete(Class<ExtCproduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}

	public void delete(ExtCproduct extCproduct) {
		ExtCproduct ext = baseDao.get(ExtCproduct.class, extCproduct.getId());
		Contract contract = baseDao.get(Contract.class, extCproduct.getContractProduct().getContract().getId());
		contract.setTotalAmount(contract.getTotalAmount()-ext.getAmount());
		baseDao.delete(ext);
	}
	

}
