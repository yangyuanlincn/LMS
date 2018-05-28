package com.linn.heima.action.sysadmin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.Module;
import com.linn.heima.domain.Role;
import com.linn.heima.service.ModuleService;
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

	
	private ModuleService moduleService;
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	
	private String moduleIds;
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
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
	
	/**
	 * 显示模块
	 */
	public String tomodule() throws Exception {
		//获取角色
		role = roleService.get(Role.class, role.getId());
		this.pushVs(role);
		return "tomodule";
	}
	
	/**
	 * 角色模块保存
	 */
	public String module() throws Exception {
		//获取角色
		role = roleService.get(Role.class, role.getId());
		
		Set<Module> modules = new HashSet<Module>();
		String[] ids = moduleIds.split(",");
		for (String id : ids) {
			modules.add(moduleService.get(Module.class, id));
		}
		role.setModules(modules);
		
		roleService.saveOrUpdate(role);
		return "alist";
	}
	
	/**
	 * 返回模块json字符串
	 * @return
	 * @throws Exception
	 */
	public String roleModuleStr()throws Exception {
		//获取角色
		role = roleService.get(Role.class, role.getId());
		//获取模块集合
		Set<Module> modules = role.getModules();
		
		//获取所有模块
		List<Module> moduleList = moduleService.find("from Module where state = 1", Module.class, null);
		//拼接json字符串
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Module module : moduleList) {
			sb.append("{\"id\":\"").append(module.getId());
			sb.append("\",\"pId\":\"").append(module.getParentId());
			sb.append("\",\"name\":\"").append(module.getName());
			if(modules.contains(module)) {
				sb.append("\",\"checked\":\"").append(true);
			}else {
				sb.append("\",\"checked\":\"").append(false);
			}
			sb.append("\"}");
			sb.append(",");
		}
		String str = sb.toString();
		str=str.substring(0, str.length()-1);
		
		str=str+"]";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(str);
		return null;
	}
}
