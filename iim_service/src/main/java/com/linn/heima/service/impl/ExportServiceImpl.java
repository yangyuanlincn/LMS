package com.linn.heima.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.linn.heima.dao.BaseDao;
import com.linn.heima.domain.Contract;
import com.linn.heima.domain.ContractProduct;
import com.linn.heima.domain.Export;
import com.linn.heima.domain.ExportProduct;
import com.linn.heima.domain.ExtCproduct;
import com.linn.heima.domain.ExtEproduct;
import com.linn.heima.service.ExportService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.UtilFuns;

public class ExportServiceImpl implements ExportService{
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Export> find(String hql, Class<Export> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Export get(Class<Export> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Export> findPage(String hql, Page<Export> page, Class<Export> entityClass, Object[] params) {
		return baseDao.findPage(hql, page,entityClass, params);
	}

	public void saveOrUpdate(Export entity) throws IllegalAccessException, InvocationTargetException{
		//新增报运单
		if(UtilFuns.isEmpty(entity.getId())) {
			entity.setState(0);//0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务
			String[] contractIds = entity.getContractIds().split(", ");
			StringBuilder sb = new StringBuilder();
			for (String id : contractIds) {
				Contract contract = baseDao.get(Contract.class, id);
				contract.setState(2);
//				baseDao.saveOrUpdate(contract);
				sb.append(contract.getContractNo()).append(" ");//合同及确定书号以逗号空格分隔
			}
			//设置合同及确定书号
			entity.setCustomerContract(sb.toString());
			entity.setContractIds(UtilFuns.joinStr(contractIds, ","));
			entity.setInputDate(new Date());
			
			
			//查询出当前购销合同下所有的货物
			String hql = "from ContractProduct where contract.id  in ("+UtilFuns.joinInStr(contractIds)+")";
			Set<ExportProduct> exportProducts = new HashSet<ExportProduct>();
			List<ContractProduct> list = baseDao.find(hql, ContractProduct.class, null);
			for (ContractProduct cp : list) {
				ExportProduct ep = new ExportProduct();
				ep.setBoxNum(cp.getBoxNum());
		    	ep.setCnumber(cp.getCnumber());
		    	ep.setFactory(cp.getFactory());
		    	ep.setOrderNo(cp.getOrderNo());
		    	ep.setPackingUnit(cp.getPackingUnit());
		    	ep.setPrice(cp.getPrice());
		    	ep.setProductNo(cp.getProductNo());
		    	ep.setExport(entity);//设置商品与报运单    多对一
		    	
		    	exportProducts.add(ep);
		    	
		    	//加载当前货物的附件列表
		    	Set<ExtCproduct> extCproducts = cp.getExtCproducts();
		    	Set<ExtEproduct> extEproducts = new HashSet<ExtEproduct>();
		    	for (ExtCproduct extCproduct : extCproducts) {
		    		ExtEproduct extEproduct = new ExtEproduct();
					BeanUtils.copyProperties(extEproduct, extCproduct);
					//注意清空id，有hibernate自动生成id
					extEproduct.setId(null);
					extEproduct.setExportProduct(ep);
					extEproducts.add(extEproduct);
				}
		    	ep.setExtEproducts(extEproducts);
			}
			entity.setExportProducts(exportProducts);
			
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Export> entitys) {
		
	}

	public void deleteById(Class<Export> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
		
	}

	public void delete(Class<Export> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}

	//更新状态
	public void changeState(String[] ids, int state) {
		for (String id : ids) {
			Export export = baseDao.get(Export.class, id);
			export.setState(state);
			//持久化对象，下面语句可以省略
			baseDao.saveOrUpdate(export);
		}
	}
	

}
