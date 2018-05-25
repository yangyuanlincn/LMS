package com.linn.heima.action.sysadmin;

import java.util.List;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.Dept;
import com.linn.heima.domain.User;
import com.linn.heima.service.DeptService;
import com.linn.heima.service.UserService;
import com.linn.heima.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends BaseAction implements ModelDriven<User>{
	private User user = new User();
	public User getModel() {
		return user;
	}	

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	//分页对象
	private Page<User> page = new Page<User>();
	
	public Page<User> getPage() {
		return page;
	}

	public void setPage(Page<User> page) {
		this.page = page;
	}



	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		userService.findPage("from User", page, User.class, null);
		page.setUrl("userAction_list");
		this.pushVs(page);
		
		return "list";
	}
	
	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		user = userService.get(User.class, user.getId());
		this.pushVs(user);
		return "toview";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String tocreate() throws Exception {
		//选择部门
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		this.setVs("deptList",deptList);
		//选择直属领导
		List<User> userList = userService.find("from User where state = 1", User.class, null);
		this.setVs("userList", userList);
		return "tocreate";
	}
	
	/**
	 * 添加
	 */
	public String insert() throws Exception {
		userService.saveOrUpdate(user);
		return "alist";
	}
	
	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		//从数据库获取
		user=userService.get(User.class, user.getId());
		this.pushVs(user);
		
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		this.setVs("deptList", deptList);
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update() throws Exception {
		//获取数据库中的数据
		User o_user = userService.get(User.class, user.getId());
		//修改属性
		o_user.setDept(user.getDept());
		o_user.setState(user.getState());
		o_user.setUserName(user.getUserName());
		userService.saveOrUpdate(o_user);
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete() throws Exception {
		String[] ids=user.getId().split(", ");
		userService.delete(User.class, ids);
		return "alist";
	}
}
