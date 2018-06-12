package com.linn.heima.action.stat;

import java.util.List;

import com.linn.heima.action.BaseAction;
import com.linn.heima.dao.springdao.SqlDao;

public class StatChartAction extends BaseAction{
	private SqlDao sqlDao;
	public void setSqlDao(SqlDao sqlDao) {
		this.sqlDao = sqlDao;
	}

	//工厂销售额
	public String factorysale() throws Exception {
		String sql = "select factory_name,sum(amount) a from contract_product_c group by factory_name order by a desc";
		List<String> list = sqlDao.executeSQL(sql);
		
		//拼接json字符串
		
//		{
//            "facotry": "ddddd",
//            "sale": 260
//        },
//        {
//            "country": "Ireland",
//            "value": 201
//        }
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<list.size();i++) {
			sb.append("{\"factory\":\"").append(list.get(i)).append("\",");
			sb.append("\"sale\":\"").append(list.get(++i)).append("\"");
			sb.append("},");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		this.setVs("factorySale", sb.toString());
		return "factorysale";
	}
	
	/*
	 * 产品销售排行
	 */
	public String productsale() throws Exception {
		String sql = "select * from (select product_no,sum(amount) amount from contract_product_c group by product_no)  where rownum < 16 order by amount desc";
		List<String> list = sqlDao.executeSQL(sql);
		/**
		 * {
                    "country": "USA",
                    "visits": 4025,
                    "color": "#FF0F00"
                },
                {
                    "country": "China",
                    "visits": 1882,
                    "color": "#FF6600"
                },
		 */
		String[] colors = {"#FF6600","#FF0F00","#FF9E01","#FCD202","#F8FF01","#B0DE09","#04D215","#0D8ECF","#0D52D1","#2A0CD0","#8A0CCF"};
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int j = 0;
		for(int i=0;i<list.size();i++) {
			if(j>=colors.length) {
				j = 0;
			}
			sb.append("{");
			sb.append("\"productNo\":\"").append(list.get(i++)).append("\",");
			sb.append("\"sale\":\"").append(list.get(i)).append("\",");
			sb.append("\"color\":\"").append(colors[j++]).append("\"");
			sb.append("},");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		
		this.setVs("productSale", sb.toString());
		return "productsale";
	}
	
	/**
	 * 系统访问压力图
	 */
	public String onlineinfo() throws Exception {
		String sql = "select a.a1 time,nvl(b.total,0) total from (select * from online_info_t) a \r\n" + 
				"left join\r\n" + 
				"(select to_char(login_time,'hh24') time,count(*) total from login_log_p group by to_char(login_time,'hh24')) b\r\n" + 
				"on a.a1=b.time\r\n" + 
				"order by a.a1";
		
		List<String> list = sqlDao.executeSQL(sql);
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<list.size();i++) {
			sb.append("{");
			sb.append("\"hour\":\"").append(list.get(i++)).append("\",");
			sb.append("\"total\":\"").append(list.get(i)).append("\"");
			sb.append("},");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		
		this.setVs("onlineInfo", sb.toString());
		return "onlineinfo";
	}
}
