package com.linn.heima.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.linn.heima.action.BaseAction;
import com.linn.heima.domain.ContractProduct;
import com.linn.heima.service.ContractProductService;
import com.linn.heima.utils.DownloadUtil;
import com.linn.heima.utils.UtilFuns;

public class OutProductAction extends BaseAction{
	private String inputDate;
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	
	private ContractProductService contractProductService;
	
	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

//	public String print() throws Exception {
//		Row nRow = null;
//		Cell nCell = null;
//		int rowNo=0,colNo=1;//从第二列开始
//		//创建工作簿
//		Workbook wb = new HSSFWorkbook();
//		
//		//创建工作表
//		Sheet sheet = wb.createSheet();
//		
//		//设置列宽
//		sheet.setColumnWidth(colNo++, 26*256);
//		sheet.setColumnWidth(colNo++, 11*256);
//		sheet.setColumnWidth(colNo++, 29*256);
//		sheet.setColumnWidth(colNo++, 12*256);
//		sheet.setColumnWidth(colNo++, 15*256);
//		sheet.setColumnWidth(colNo++, 10*256);
//		sheet.setColumnWidth(colNo++, 10*256);
//		sheet.setColumnWidth(colNo++, 8*256);
//		
//		colNo = 1;
//		
//		//创建行对象
//		nRow = sheet.createRow(rowNo++);
//		//设置行高
//		nRow.setHeightInPoints(38);
//		nCell = nRow.createCell(colNo);
//		
//		//合并单元格
//		sheet.addMergedRegion(new CellRangeAddress(0,0,1,8));
//		
//		//设置单元格的内容
//		nCell.setCellValue(inputDate);
//		
//		//设置单元格样式
//		nCell.setCellStyle(this.bigTitle(wb));
//		
//		String titles[] = {"客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};
//		
//		//创建小标题行对象
//		nRow = sheet.createRow(rowNo++);
//		nRow.setHeightInPoints(26.25f);
//		
//		// 创建单元格对象，设置内容和样式
//		colNo = 1;
//		for (String title : titles) {
//			nCell = nRow.createCell(colNo++);
//			nCell.setCellValue(title);
//			nCell.setCellStyle(this.title(wb));
//		}
//		
//		
//		String hql = "from ContractProduct where to_char(contract.shipTime,'yyyy-MM') = '"+inputDate+"'";
//		
//		List<ContractProduct> list = contractProductService.find(hql, ContractProduct.class, null);
//		for (ContractProduct cp : list) {
//			colNo = 1;
//			nRow = sheet.createRow(rowNo++);
//			nRow.setHeightInPoints(30);
//			
//			nCell = nRow.createCell(colNo++);//产生单元格对象
//			nCell.setCellValue(cp.getContract().getCustomName());//客户名称
//			nCell.setCellStyle(this.text(wb));//设置文本样式
//			
//			nCell = nRow.createCell(colNo++);//产生单元格对象
//			nCell.setCellValue(cp.getContract().getContractNo());//订单号--- 合同号
//			nCell.setCellStyle(this.text(wb));//设置文本样式
//			
//			nCell = nRow.createCell(colNo++);//产生单元格对象
//			nCell.setCellValue(cp.getProductNo());     //        货号
//			nCell.setCellStyle(this.text(wb));//设置文本样式
//			
//			
//			nCell = nRow.createCell(colNo++);//产生单元格对象
//			nCell.setCellValue(cp.getCnumber());//      数量 
//			nCell.setCellStyle(this.text(wb));//设置文本样式
//			
//			
//			nCell = nRow.createCell(colNo++);//产生单元格对象
//			nCell.setCellValue(cp.getFactoryName());//工厂名
//			nCell.setCellStyle(this.text(wb));//设置文本样式
//			
//			
//			nCell = nRow.createCell(colNo++);//产生单元格对象
//			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getDeliveryPeriod()));//交期
//			nCell.setCellStyle(this.text(wb));//设置文本样式
//			
//			
//			nCell = nRow.createCell(colNo++);//产生单元格对象
//			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getShipTime()));//船期
//			nCell.setCellStyle(this.text(wb));//设置文本样式
//			
//			
//			nCell = nRow.createCell(colNo++);//产生单元格对象
//			nCell.setCellValue(cp.getContract().getTradeTerms());//贸易条款
//			nCell.setCellStyle(this.text(wb));//设置文本样式
//		}
//		
//		
////		//输出流
////		HttpServletResponse response = ServletActionContext.getResponse();
////		String mimeType = ServletActionContext.getServletContext().getMimeType(".xls");
////		DownloadUtil download = new DownloadUtil();
////		response.setContentType(mimeType);
////		response.setHeader("content-disposition", "attachment;filename="+download.encodeDownloadFilename("出货表.xls",ServletActionContext.getRequest().getHeader("user-agent") ));
////		ServletOutputStream outputStream = response.getOutputStream();
////		wb.write(outputStream);
//		
//		//调用工具
//		DownloadUtil download = new DownloadUtil();
//		HttpServletResponse response = ServletActionContext.getResponse();
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		wb.write(bos);
//		bos.flush();
//		
//		download.download(bos, response, "出货表.xls");
//		return null;
//	}
	
	//使用模板打印
	public String print() throws Exception {
		//通用变量
		int rowNo=0,cellNo=1;
		Row nRow =null;
		Cell nCell = null;
		
		
		//1.读取工作簿
		String path = ServletActionContext.getServletContext().getRealPath("/")+"/make/xlsprint/tOUTPRODUCT.xls";
		System.out.println(path);
		
		InputStream is = new FileInputStream(path);
		Workbook wb = new HSSFWorkbook(is);
		
		//2.读取工作表
		Sheet sheet = wb.getSheetAt(0);
		


		cellNo=1;//重置
		
		//3.创建行对象
		//=========================================大标题=============================
		nRow = sheet.getRow(rowNo++);//读取行对象
		nCell = nRow.getCell(cellNo);
		//设置单元格的内容
		nCell.setCellValue(inputDate.replace("-0", "-").replace("-", "年")+"月份出货表");//2015-01   2015-12
		
		
		
		
		//=======================================小标题=================================
		rowNo++;
		
		//=======================================数据输出=================================================
		nRow = sheet.getRow(rowNo);//读取第三行
		CellStyle customCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle orderNoCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle productNoCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle cNumberCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle factoryCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle deliveryPeriodCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle shipTimeCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle tradeTermsCellStyle = nRow.getCell(cellNo++).getCellStyle();
		
		
		
		String hql= "from ContractProduct  where to_char(contract.shipTime,'yyyy-MM') ='"+inputDate+"'";
		List<ContractProduct> list = contractProductService.find(hql, ContractProduct.class, null);//查询出符合指定船期的货物列表
		
		for(ContractProduct cp :list){
			nRow = sheet.createRow(rowNo++);//产生数据行
			nRow.setHeightInPoints(24);//设置行高
			
			cellNo=1;
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getContract().getCustomName());//客户名称
			nCell.setCellStyle(customCellStyle);//设置文本样式
			
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getContract().getContractNo());//订单号--- 合同号
			nCell.setCellStyle(orderNoCellStyle);//设置文本样式
			
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getProductNo());     //        货号
			nCell.setCellStyle(productNoCellStyle);//设置文本样式
			
			
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getCnumber());//      数量 
			nCell.setCellStyle(cNumberCellStyle);//设置文本样式
			
			
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getFactoryName());//工厂名
			nCell.setCellStyle(factoryCellStyle);//设置文本样式
			
			
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getDeliveryPeriod()));//交期
			nCell.setCellStyle(deliveryPeriodCellStyle);//设置文本样式
			
			
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getShipTime()));//船期
			nCell.setCellStyle(shipTimeCellStyle);//设置文本样式
			
			
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getContract().getTradeTerms());//贸易条款
			nCell.setCellStyle(tradeTermsCellStyle);//设置文本样式
			
		}
		
		
		
		
		
		//======================================输出到客户端（下载）========================================
		DownloadUtil downUtil = new DownloadUtil();
		
		ByteArrayOutputStream  baos = new ByteArrayOutputStream();//流  内存中的缓存区
		wb.write(baos);//将excel表格中的内容输出到缓存
		baos.close();//刷新缓存
		
		
		HttpServletResponse response = ServletActionContext.getResponse();//得到response对象
		
		downUtil.download(baos, response, "出货表.xls");
		
		
		
		return NONE;
	}

	
	public String toedit() throws Exception {
		return "toedit";
	}
	// 大标题的样式
	public CellStyle bigTitle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 字体加粗

		style.setFont(font);

		style.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中

		return style;
	}

	// 小标题的样式
	public CellStyle title(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);

		style.setFont(font);

		style.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中

		style.setBorderTop(CellStyle.BORDER_THIN); // 上细线
		style.setBorderBottom(CellStyle.BORDER_THIN); // 下细线
		style.setBorderLeft(CellStyle.BORDER_THIN); // 左细线
		style.setBorderRight(CellStyle.BORDER_THIN); // 右细线

		return style;
	}

	// 文字样式
	public CellStyle text(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short) 10);

		style.setFont(font);

		style.setAlignment(CellStyle.ALIGN_LEFT); // 横向居左
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中

		style.setBorderTop(CellStyle.BORDER_THIN); // 上细线
		style.setBorderBottom(CellStyle.BORDER_THIN); // 下细线
		style.setBorderLeft(CellStyle.BORDER_THIN); // 左细线
		style.setBorderRight(CellStyle.BORDER_THIN); // 右细线

		return style;
	}
}
