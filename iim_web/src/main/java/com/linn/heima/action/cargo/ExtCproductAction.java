package com.linn.heima.action.cargo;

import java.util.List;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.ExtCproduct;
import com.linn.heima.domain.Factory;
import com.linn.heima.service.ExtCproductService;
import com.linn.heima.service.FactoryService;
import com.linn.heima.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

public class ExtCproductAction extends BaseAction implements ModelDriven<ExtCproduct>{
	private ExtCproduct extCproduct = new ExtCproduct();
	public ExtCproduct getModel() {
		return extCproduct;
	}	

	private ExtCproductService extCproductService;
	public void setExtCproductService(ExtCproductService extCproductService) {
		this.extCproductService = extCproductService;
	}
	
	private FactoryService factoryService;
	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	//分页对象
	private Page<ExtCproduct> page = new Page<ExtCproduct>();
	
	public Page<ExtCproduct> getPage() {
		return page;
	}

	public void setPage(Page<ExtCproduct> page) {
		this.page = page;
	}



	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		extCproductService.findPage("from ExtCproduct", page, ExtCproduct.class, null);
		page.setUrl("userAction_list");
		this.pushVs(page);
		
		return "list";
	}
	
	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		extCproduct = extCproductService.get(ExtCproduct.class, extCproduct.getId());
		this.pushVs(extCproduct);
		return "toview";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String tocreate() throws Exception {
		//查询附件列表
		extCproductService.findPage("from ExtCproduct where contractProduct.id = ?", page, ExtCproduct.class, new Object[] {extCproduct.getContractProduct().getId()});
		//查询工厂
		List<Factory> factoryList = factoryService.find("from Factory where state = 1", Factory.class, null);
		this.setVs("factoryList", factoryList);
		this.pushVs(page);
		
		return "tocreate";
	}
	
	/**
	 * 添加
	 */
	public String insert() throws Exception {
		extCproductService.saveOrUpdate(extCproduct);
		return "alist";
	}
	
	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		ExtCproduct obj = extCproductService.get(ExtCproduct.class, extCproduct.getId());
		List<Factory> factoryList = factoryService.find("from Factory where state = 1", Factory.class, null);
		this.setVs("factoryList", factoryList);
		this.pushVs(obj);
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update() throws Exception {
		ExtCproduct obj = extCproductService.get(ExtCproduct.class, extCproduct.getId());
		obj.setFactory(extCproduct.getFactory());
		obj.setFactoryName(extCproduct.getFactoryName());
		obj.setProductNo(extCproduct.getProductNo());
		obj.setProductImage(extCproduct.getProductImage());
		obj.setCnumber(extCproduct.getCnumber());
		obj.setPackingUnit(extCproduct.getPackingUnit());
		obj.setPrice(extCproduct.getPrice());
		obj.setOrderNo(extCproduct.getOrderNo());
		obj.setProductDesc(extCproduct.getProductDesc());
		obj.setProductRequest(extCproduct.getProductRequest());
		
		extCproductService.saveOrUpdate(obj);
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete() throws Exception {
		extCproductService.delete(extCproduct);
		return "alist";
	}
	
}
