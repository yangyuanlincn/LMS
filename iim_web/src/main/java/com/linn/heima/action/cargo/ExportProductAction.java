package com.linn.heima.action.cargo;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.ExportProduct;
import com.linn.heima.domain.User;
import com.linn.heima.service.ExportProductService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.SysConstant;
import com.opensymphony.xwork2.ModelDriven;

public class ExportProductAction extends BaseAction implements ModelDriven<ExportProduct>{
	private ExportProduct exportProduct = new ExportProduct();
	public ExportProduct getModel() {
		return exportProduct;
	}	

	private ExportProductService exportProductService;
	public void setExportProductService(ExportProductService exportProductService) {
		this.exportProductService = exportProductService;
	}

	
	//分页对象
	private Page<ExportProduct> page = new Page<ExportProduct>();
	
	public Page<ExportProduct> getPage() {
		return page;
	}

	public void setPage(Page<ExportProduct> page) {
		this.page = page;
	}



	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		String hql = "from ExportProduct where 1=1";
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		Integer degree = user.getUserInfo().getDegree();
		if(degree == 4) {
			//普通员工只能查看自己创建的合同
			hql=hql+"and createBy = '"+user.getId()+"'";
		}else if(degree == 3) {
			//部门经理可以查看本部门下员工创建的合同
			hql=hql+"and createDept = '"+user.getDept().getId()+"'";
		}
		exportProductService.findPage(hql, page, ExportProduct.class,null);
		page.setUrl("exportProductAction_list");
		this.pushVs(page);
		
		return "list";
	}
	
	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		exportProduct = exportProductService.get(ExportProduct.class, exportProduct.getId());
		this.pushVs(exportProduct);
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
		exportProduct.setCreateBy(user.getId());
		exportProduct.setCreateDept(user.getDept().getId());
		exportProductService.saveOrUpdate(exportProduct);
		return "alist";
	}
	
	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		//从数据库获取
		exportProduct=exportProductService.get(ExportProduct.class, exportProduct.getId());
		this.pushVs(exportProduct);
		
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update() throws Exception {
		//获取数据库中的数据
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete() throws Exception {
		String[] ids=exportProduct.getId().split(", ");
		exportProductService.delete(ExportProduct.class, ids);
		return "alist";
	}
	
	/**
	 * 提交
	 */
	public String submit() throws Exception {
		String[] ids = exportProduct.getId().split(", ");
		return "alist";
	}
	
	/**
	 * 撤销
	 */
	public String cancel() throws Exception {
		String[] ids = exportProduct.getId().split(", ");
		return "alist";
	}
	
	/**
	 * 打印
	 */
	public String print() throws Exception {
		
		return "print";
	}
}
