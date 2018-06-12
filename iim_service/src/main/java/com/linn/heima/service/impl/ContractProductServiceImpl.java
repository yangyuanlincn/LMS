package com.linn.heima.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import com.linn.heima.dao.BaseDao;
import com.linn.heima.domain.Contract;
import com.linn.heima.domain.ContractProduct;
import com.linn.heima.service.ContractProductService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.UtilFuns;

public class ContractProductServiceImpl implements ContractProductService{
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ContractProduct> find(String hql, Class<ContractProduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public ContractProduct get(Class<ContractProduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<ContractProduct> findPage(String hql, Page<ContractProduct> page, Class<ContractProduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page,entityClass, params);
	}

	public void saveOrUpdate(ContractProduct entity) {
		double amount = 0d;
		//新增
		if(UtilFuns.isEmpty(entity.getId())) {
			if(UtilFuns.isNotEmpty(entity.getPrice()) && UtilFuns.isNotEmpty(entity.getCnumber())) {
				amount = entity.getPrice()*entity.getCnumber();
			}
			//默认值为0;如果不设置下方修改会报错
			entity.setAmount(amount);
			Contract contract = baseDao.get(Contract.class, entity.getContract().getId());
			contract.setTotalAmount(contract.getTotalAmount()+amount);
			baseDao.saveOrUpdate(contract);
		}else {
			//修改
			//原来的价格
			double old = entity.getAmount();
			if(UtilFuns.isNotEmpty(entity.getPrice()) && UtilFuns.isNotEmpty(entity.getCnumber())) {
				amount = entity.getPrice()*entity.getCnumber();
			}
			entity.setAmount(amount);
			Contract contract = baseDao.get(Contract.class, entity.getContract().getId());
			contract.setTotalAmount(contract.getTotalAmount()+amount-old);
			baseDao.saveOrUpdate(contract);
		}
		//更新货物
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ContractProduct> entitys) {
		
	}

	public void deleteById(Class<ContractProduct> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
		
	}

	public void delete(Class<ContractProduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}

	public void delete(ContractProduct obj) {
		ContractProduct contractProduct = baseDao.get(ContractProduct.class, obj.getId());
		Contract contract = baseDao.get(Contract.class, obj.getContract().getId());
		contract.setTotalAmount(contract.getTotalAmount()-contractProduct.getAmount());
		baseDao.delete(contractProduct);
	}


}
