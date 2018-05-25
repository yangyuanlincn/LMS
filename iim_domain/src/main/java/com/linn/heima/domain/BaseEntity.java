package com.linn.heima.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

	private String createBy;  //创建者的 id
	private String createDept;//创建者所在部门的id
	private Date createTime;//创建时间
	private String updateBy;//更新者的id
	private Date updateTime;//更新时间
	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
