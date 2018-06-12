package com.linn.heima.action.cargo;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.ExtEproduct;
import com.linn.heima.domain.User;
import com.linn.heima.service.ExtEproductService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.SysConstant;
import com.opensymphony.xwork2.ModelDriven;

public class ExtEproductAction extends BaseAction implements ModelDriven<ExtEproduct>{
	private ExtEproduct extEproduct = new ExtEproduct();
	public ExtEproduct getModel() {
		return extEproduct;
	}	

	private ExtEproductService extEproductService;
	public void setExtEproductService(ExtEproductService extEproductService) {
		this.extEproductService = extEproductService;
	}

	
	//分页对象
	private Page<ExtEproduct> page = new Page<ExtEproduct>();
	
	public Page<ExtEproduct> getPage() {
		return page;
	}

	public void setPage(Page<ExtEproduct> page) {
		this.page = page;
	}



	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		String hql = "from ExtEproduct where 1=1";
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		Integer degree = user.getUserInfo().getDegree();
		if(degree == 4) {
			//普通员工只能查看自己创建的合同
			hql=hql+"and createBy = '"+user.getId()+"'";
		}else if(degree == 3) {
			//部门经理可以查看本部门下员工创建的合同
			hql=hql+"and createDept = '"+user.getDept().getId()+"'";
		}
		extEproductService.findPage(hql, page, ExtEproduct.class,null);
		page.setUrl("contractAction_list");
		this.pushVs(page);
		
		return "list";
	}
	
	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		extEproduct = extEproductService.get(ExtEproduct.class, extEproduct.getId());
		this.pushVs(extEproduct);
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
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		extEproduct.setCreateBy(user.getId());
		extEproduct.setCreateDept(user.getDept().getId());
		extEproductService.saveOrUpdate(extEproduct);
		return "alist";
	}
	
	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		//从数据库获取
		extEproduct=extEproductService.get(ExtEproduct.class, extEproduct.getId());
		this.pushVs(extEproduct);
		
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
		String[] ids=extEproduct.getId().split(", ");
		extEproductService.delete(ExtEproduct.class, ids);
		return "alist";
	}
	
	/**
	 * 提交
	 */
	public String submit() throws Exception {
		String[] ids = extEproduct.getId().split(", ");
		return "alist";
	}
	
	/**
	 * 撤销
	 */
	public String cancel() throws Exception {
		String[] ids = extEproduct.getId().split(", ");
		return "alist";
	}
	
	/**
	 * 打印
	 */
	public String print() throws Exception {
		
		return "print";
	}
}
