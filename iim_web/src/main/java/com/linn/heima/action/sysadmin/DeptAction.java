package com.linn.heima.action.sysadmin;

import java.util.List;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.Dept;
import com.linn.heima.service.DeptService;
import com.linn.heima.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

public class DeptAction extends BaseAction implements ModelDriven<Dept>{
	private Dept dept = new Dept();
	public Dept getModel() {
		return dept;
	}	

	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}


	//分页对象
	private Page<Dept> page = new Page<Dept>();
	
	public Page<Dept> getPage() {
		return page;
	}

	public void setPage(Page<Dept> page) {
		this.page = page;
	}



	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		deptService.findPage("from Dept", page, Dept.class, null);
		page.setUrl("deptAction_list");
		this.pushVs(page);
		
		return "list";
	}
	
	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		dept = deptService.get(Dept.class, dept.getId());
		this.pushVs(dept);
		return "toview";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String tocreate() throws Exception {
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		this.setVs("deptList",deptList);
		return "tocreate";
	}
	
	/**
	 * 添加
	 */
	public String insert() throws Exception {
		deptService.saveOrUpdate(dept);
		return "alist";
	}
	
	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		
		dept=deptService.get(Dept.class,dept.getId());
		this.pushVs(dept);
		List<Dept> deptList=deptService.find("from Dept where state = 1", Dept.class, null);
		//不显示自身
		deptList.remove(dept);
		this.setVs("deptList", deptList);
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update() throws Exception {
		//获取数据库中的数据
		Dept dept2 = deptService.get(Dept.class, dept.getId());
		//修改属性
		dept2.setParent(dept.getParent());
		dept2.setDeptName(dept.getDeptName());
		
		deptService.saveOrUpdate(dept2);
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete() throws Exception {
		String[] ids=dept.getId().split(", ");
		deptService.delete(Dept.class, ids);
		return "alist";
	}
}
