package com.linn.heima.action.cargo;

import java.util.List;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.ContractProduct;
import com.linn.heima.domain.Factory;
import com.linn.heima.service.ContractProductService;
import com.linn.heima.service.FactoryService;
import com.linn.heima.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

public class ContractProductAction extends BaseAction implements ModelDriven<ContractProduct>{
	private ContractProduct contractProduct = new ContractProduct();
	public ContractProduct getModel() {
		return contractProduct;
	}	

	private ContractProductService contractProductService;
	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

	private FactoryService factoryService;
	
	
	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	//分页对象
	private Page<ContractProduct> page = new Page<ContractProduct>();
	
	public Page<ContractProduct> getPage() {
		return page;
	}

	public void setPage(Page<ContractProduct> page) {
		this.page = page;
	}



	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		contractProductService.findPage("from ContractProduct", page, ContractProduct.class, null);
		page.setUrl("userAction_list");
		this.pushVs(page);
		
		return "list";
	}
	
	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		contractProduct = contractProductService.get(ContractProduct.class, contractProduct.getId());
		this.pushVs(contractProduct);
		return "toview";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String tocreate() throws Exception {
		//查询工厂列表
		List<Factory> factoryList = factoryService.find("from Factory where state = 1", Factory.class, null);
		this.setVs("factoryList", factoryList);
		//查询当前购销合同下的货物
		contractProductService.findPage("from ContractProduct where contract.id = ?", page, ContractProduct.class, new Object[] {contractProduct.getContract().getId()});
		page.setUrl("contractProductAction_tocreate");
		this.pushVs(page);
		return "tocreate";
	}
	
	/**
	 * 添加
	 */
	public String insert() throws Exception {
		contractProductService.saveOrUpdate(contractProduct);
		return "alist";
	}
	
	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		//从数据库获取
		contractProduct=contractProductService.get(ContractProduct.class, contractProduct.getId());
		//工厂列表
		List<Factory> factoryList = factoryService.find("from Factory where state = 1", Factory.class, null);
		this.pushVs(contractProduct);
		this.setVs("factoryList", factoryList);
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update() throws Exception {
		ContractProduct obj = contractProductService.get(ContractProduct.class, contractProduct.getId());
		obj.setFactory(contractProduct.getFactory());
		obj.setFactoryName(contractProduct.getFactoryName());
		obj.setProductNo(contractProduct.getProductNo());
		obj.setProductImage(contractProduct.getProductImage());
		obj.setCnumber(contractProduct.getCnumber());
		obj.setPackingUnit(contractProduct.getPackingUnit());
		obj.setLoadingRate(contractProduct.getLoadingRate());
		obj.setBoxNum(contractProduct.getBoxNum());
		obj.setPrice(contractProduct.getPrice());
		obj.setOrderNo(contractProduct.getOrderNo());
		obj.setProductDesc(contractProduct.getProductDesc());
		obj.setProductRequest(contractProduct.getProductRequest());
		contractProductService.saveOrUpdate(obj);
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete() throws Exception {
		contractProductService.delete(contractProduct);
		return "alist";
	}
	
}
