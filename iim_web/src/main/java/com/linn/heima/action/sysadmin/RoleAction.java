package com.linn.heima.action.sysadmin;

import java.util.List;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.Role;
import com.linn.heima.service.RoleService;
import com.linn.heima.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

public class RoleAction extends BaseAction implements ModelDriven<Role>{
	private Role role = new Role();
	public Role getModel() {
		return role;
	}	

	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	//分页对象
	private Page<Role> page = new Page<Role>();
	
	public Page<Role> getPage() {
		return page;
	}

	public void setPage(Page<Role> page) {
		this.page = page;
	}



	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		roleService.findPage("from Role", page, Role.class, null);
		page.setUrl("roleAction_list");
		this.pushVs(page);
		
		return "list";
	}
	
	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		role = roleService.get(Role.class, role.getId());
		this.pushVs(role);
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
		roleService.saveOrUpdate(role);
		return "alist";
	}
	
	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		//从数据库获取
		role=roleService.get(Role.class, role.getId());
		this.pushVs(role);
		
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update() throws Exception {
		//获取数据库中的数据
		Role o_role = roleService.get(Role.class, role.getId());
		//修改属性
		o_role.setName(role.getName());
		o_role.setRemark(role.getRemark());
		roleService.saveOrUpdate(o_role);
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete() throws Exception {
		String[] ids=role.getId().split(", ");
		roleService.delete(Role.class, ids);
		return "alist";
	}
}
