package com.linn.heima.action.sysadmin;

import java.util.List;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.Module;
import com.linn.heima.service.ModuleService;
import com.linn.heima.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

public class ModuleAction extends BaseAction implements ModelDriven<Module>{
	private Module module = new Module();
	public Module getModel() {
		return module;
	}	

	private ModuleService moduleService;
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}


	//分页对象
	private Page<Module> page = new Page<Module>();
	
	public Page<Module> getPage() {
		return page;
	}

	public void setPage(Page<Module> page) {
		this.page = page;
	}



	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		moduleService.findPage("from Module", page, Module.class, null);
		page.setUrl("moduleAction_list");
		this.pushVs(page);
		
		return "list";
	}
	
	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		module = moduleService.get(Module.class, module.getId());
		this.pushVs(module);
		return "toview";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String tocreate() throws Exception {
		return "tocreate";
	}
	
	/**
	 * 添加
	 */
	public String insert() throws Exception {
		moduleService.saveOrUpdate(module);
		return "alist";
	}
	
	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		//从数据库获取
		module=moduleService.get(Module.class, module.getId());
		this.pushVs(module);
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update() throws Exception {
		//获取数据库中的数据
		Module o_module = moduleService.get(Module.class, module.getId());
		//修改属性
		o_module.setName(module.getName());
		o_module.setLayerNum(module.getLayerNum());
		o_module.setCpermission(module.getCpermission());
		o_module.setCurl(module.getCurl());
		o_module.setCtype(module.getCtype());
		o_module.setState(module.getState());
		o_module.setBelong(module.getBelong());
		o_module.setCwhich(module.getCwhich());
		o_module.setRemark(module.getRemark());
		o_module.setOrderNo(module.getOrderNo());
		moduleService.saveOrUpdate(o_module);
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete() throws Exception {
		String[] ids=module.getId().split(", ");
		moduleService.delete(Module.class, ids);
		return "alist";
	}
}
