package com.linn.heima.action.cargo;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.Contract;
import com.linn.heima.domain.User;
import com.linn.heima.service.ContractService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.SysConstant;
import com.opensymphony.xwork2.ModelDriven;

public class ContractAction extends BaseAction implements ModelDriven<Contract>{
	private Contract contract = new Contract();
	public Contract getModel() {
		return contract;
	}	

	private ContractService contractService;
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	
	//分页对象
	private Page<Contract> page = new Page<Contract>();
	
	public Page<Contract> getPage() {
		return page;
	}

	public void setPage(Page<Contract> page) {
		this.page = page;
	}



	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		String hql = "from Contract where 1=1";
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		Integer degree = user.getUserInfo().getDegree();
		if(degree == 4) {
			//普通员工只能查看自己创建的合同
			hql=hql+"and createBy = '"+user.getId()+"'";
		}else if(degree == 3) {
			//部门经理可以查看本部门下员工创建的合同
			hql=hql+"and createDept = '"+user.getDept().getId()+"'";
		}
		contractService.findPage(hql, page, Contract.class,null);
		page.setUrl("contractAction_list");
		this.pushVs(page);
		
		return "list";
	}
	
	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		contract = contractService.get(Contract.class, contract.getId());
		this.pushVs(contract);
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
		contract.setCreateBy(user.getId());
		contract.setCreateDept(user.getDept().getId());
		contractService.saveOrUpdate(contract);
		return "alist";
	}
	
	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		//从数据库获取
		contract=contractService.get(Contract.class, contract.getId());
		this.pushVs(contract);
		
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update() throws Exception {
		//获取数据库中的数据
		Contract obj = contractService.get(Contract.class, contract.getId());
		//修改属性
		obj.setCustomName(contract.getCustomName());
		obj.setPrintStyle(contract.getPrintStyle());
		obj.setContractNo(contract.getContractNo());
		obj.setOfferor(contract.getOfferor());
		obj.setInputBy(contract.getInputBy());
		obj.setCheckBy(contract.getCheckBy());
		obj.setInspector(contract.getInspector());
		obj.setSigningDate(contract.getSigningDate());
		obj.setImportNum(contract.getImportNum());
		obj.setShipTime(contract.getShipTime());
		obj.setTradeTerms(contract.getTradeTerms());
		obj.setDeliveryPeriod(contract.getDeliveryPeriod());
		obj.setCrequest(contract.getCrequest());
		obj.setRemark(contract.getRemark());
		//保存
		contractService.saveOrUpdate(obj);
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete() throws Exception {
		String[] ids=contract.getId().split(", ");
		contractService.delete(Contract.class, ids);
		return "alist";
	}
	
	/**
	 * 提交
	 */
	public String submit() throws Exception {
		String[] ids = contract.getId().split(", ");
		contractService.changeState(ids,1);
		return "alist";
	}
	
	/**
	 * 撤销
	 */
	public String cancel() throws Exception {
		String[] ids = contract.getId().split(", ");
		contractService.changeState(ids,0);
		return "alist";
	}
	
	/**
	 * 打印
	 */
	public String print() throws Exception {
		
		return "print";
	}
}
