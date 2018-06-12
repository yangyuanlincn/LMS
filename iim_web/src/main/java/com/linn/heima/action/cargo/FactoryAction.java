package com.linn.heima.action.cargo;

import java.util.List;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.Factory;
import com.linn.heima.service.FactoryService;
import com.linn.heima.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

public class FactoryAction extends BaseAction implements ModelDriven<Factory>{
	private Factory factory = new Factory();
	public Factory getModel() {
		return factory;
	}	

	private FactoryService factoryService;
	public void setContractService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	
	//分页对象
	private Page<Factory> page = new Page<Factory>();
	
	public Page<Factory> getPage() {
		return page;
	}

	public void setPage(Page<Factory> page) {
		this.page = page;
	}



	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		factoryService.findPage("from Factory", page, Factory.class, null);
		page.setUrl("userAction_list");
		this.pushVs(page);
		
		return "list";
	}
	
	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		factory = factoryService.get(Factory.class, factory.getId());
		this.pushVs(factory);
		return "toview";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String tocreate() throws Exception {
		//选择部门
		//选择直属领导
		List<Factory> userList = factoryService.find("from Factory where state = 1", Factory.class, null);
		this.setVs("userList", userList);
		return "tocreate";
	}
	
	/**
	 * 添加
	 */
	public String insert() throws Exception {
		factoryService.saveOrUpdate(factory);
		return "alist";
	}
	
	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		//从数据库获取
		factory=factoryService.get(Factory.class, factory.getId());
		this.pushVs(factory);
		
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update() throws Exception {
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete() throws Exception {
		String[] ids=factory.getId().split(", ");
		factoryService.delete(Factory.class, ids);
		return "alist";
	}
	
}
