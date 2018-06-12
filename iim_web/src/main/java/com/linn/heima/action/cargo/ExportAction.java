package com.linn.heima.action.cargo;

import java.util.HashSet;
import java.util.Set;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.Contract;
import com.linn.heima.domain.Export;
import com.linn.heima.domain.ExportProduct;
import com.linn.heima.domain.User;
import com.linn.heima.service.ContractService;
import com.linn.heima.service.ExportProductService;
import com.linn.heima.service.ExportService;
import com.linn.heima.utils.Page;
import com.linn.heima.utils.UtilFuns;
import com.opensymphony.xwork2.ModelDriven;

public class ExportAction extends BaseAction implements ModelDriven<Export> {
	private Export export = new Export();

	public Export getModel() {
		return export;
	}

	private ExportProductService exportProductService;

	public void setExportProductService(ExportProductService exportProductService) {
		this.exportProductService = exportProductService;
	}

	private ExportService exportService;

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	private ContractService contractService;

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	// 分页对象
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page<Export> page) {
		this.page = page;
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		exportService.findPage("from Export", page, Export.class, null);
		this.pushVs(page);
		page.setUrl("exportAction_list");
		return "list";
	}

	/**
	 * 跳转到详情页面
	 */
	public String toview() throws Exception {
		export = exportService.get(Export.class, export.getId());
		this.pushVs(export);
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
		User user = super.getCurrUser();
		export.setCreateBy(user.getId());
		export.setCreateDept(user.getDept().getId());
		exportService.saveOrUpdate(export);
		return contractList();
	}

	/**
	 * 修改页面
	 */
	public String toupdate() throws Exception {
		// addTRRecord(objId, id, productNo, cnumber, grossWeight, netWeight,
		// sizeLength, sizeWidth, sizeHeight, exPrice, tax)
		// 从数据库获取
		Export obj = exportService.get(Export.class, export.getId());
		this.pushVs(obj);

		// 查询所有货物
		Set<ExportProduct> exportProducts = obj.getExportProducts();
		// 拼接字符串
		StringBuilder sb = new StringBuilder();
		for (ExportProduct ep : exportProducts) {
			sb.append("addTRRecord(\"").append("mRecordTable");
			sb.append("\", \"").append(ep.getId());
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getProductNo()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getCnumber()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getGrossWeight()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getNetWeight()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeLength()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeWidth()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeHeight()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getExPrice()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getTax())).append("\");");
		}
		this.setVs("mRecordData", sb.toString());
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 调用业务
		Export obj = exportService.get(Export.class, export.getId());// 根据id,得到一个数据库中保存的对象

		// 2.设置修改的属性
		obj.setInputDate(export.getInputDate());

		obj.setLcno(export.getLcno());
		obj.setConsignee(export.getConsignee());
		obj.setShipmentPort(export.getShipmentPort());
		obj.setDestinationPort(export.getDestinationPort());
		obj.setTransportMode(export.getTransportMode());
		obj.setPriceCondition(export.getPriceCondition());
		obj.setMarks(export.getMarks());// 唛头是指具有一定格式的备注信息
		obj.setRemark(export.getRemark());

		
		Set<ExportProduct> exportProducts = new HashSet<ExportProduct>();
		for (int i = 0; i < mr_id.length; i++) {
			// 遍历数组，得到每个商品对象
			ExportProduct ep = exportProductService.get(ExportProduct.class, mr_id[i]);

			if ("1".equals(mr_changed[i])) {
				ep.setCnumber(mr_cnumber[i]);
				ep.setGrossWeight(mr_grossWeight[i]);
				ep.setNetWeight(mr_netWeight[i]);
				ep.setSizeLength(mr_sizeLength[i]);
				ep.setSizeWidth(mr_sizeWidth[i]);
				ep.setSizeHeight(mr_sizeHeight[i]);
				ep.setExPrice(mr_exPrice[i]);
				ep.setTax(mr_tax[i]);

			}

			exportProducts.add(ep);
		}
		obj.setExportProducts(exportProducts);
		exportService.saveOrUpdate(obj);
		return "alist";
	}

	private String mr_changed[];
	private String mr_id[];
	private Integer mr_cnumber[];
	private Double mr_grossWeight[];
	private Double mr_netWeight[];
	private Double mr_sizeLength[];
	private Double mr_sizeWidth[];
	private Double mr_sizeHeight[];
	private Double mr_exPrice[];
	private Double mr_tax[];

	public void setMr_changed(String[] mr_changed) {
		this.mr_changed = mr_changed;
	}

	public void setMr_id(String[] mr_id) {
		this.mr_id = mr_id;
	}

	public void setMr_cnumber(Integer[] mr_cnumber) {
		this.mr_cnumber = mr_cnumber;
	}

	public void setMr_grossWeight(Double[] mr_grossWeight) {
		this.mr_grossWeight = mr_grossWeight;
	}

	public void setMr_netWeight(Double[] mr_netWeight) {
		this.mr_netWeight = mr_netWeight;
	}

	public void setMr_sizeLength(Double[] mr_sizeLength) {
		this.mr_sizeLength = mr_sizeLength;
	}

	public void setMr_sizeWidth(Double[] mr_sizeWidth) {
		this.mr_sizeWidth = mr_sizeWidth;
	}

	public void setMr_sizeHeight(Double[] mr_sizeHeight) {
		this.mr_sizeHeight = mr_sizeHeight;
	}

	public void setMr_exPrice(Double[] mr_exPrice) {
		this.mr_exPrice = mr_exPrice;
	}

	public void setMr_tax(Double[] mr_tax) {
		this.mr_tax = mr_tax;
	}

	/**
	 * 删除
	 */
	public String delete() throws Exception {
		String[] ids = export.getId().split(", ");
		exportService.delete(Export.class, ids);
		return "alist";
	}

	/**
	 * 提交
	 */
	public String submit() throws Exception {
		String[] ids = export.getId().split(", ");
		exportService.changeState(ids, 1);
		return "alist";
	}

	/**
	 * 撤销
	 */
	public String cancel() throws Exception {
		String[] ids = export.getId().split(", ");
		exportService.changeState(ids,0);
		return "alist";
	}

	/**
	 * 打印
	 */
	public String print() throws Exception {

		return "print";
	}

	public String contractList() throws Exception {
		String hql = "from Contract where state = 1";
		contractService.findPage(hql, page, Contract.class, null);
		page.setUrl("exportAction_contractList");
		this.pushVs(page);
		return "contractList";
	}
}
