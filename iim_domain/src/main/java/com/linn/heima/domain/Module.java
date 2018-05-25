package com.linn.heima.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Module extends BaseEntity implements Serializable {
	private Set<Role> roles = new HashSet<Role>(0);
	private String id;
	private String parentId;//父模块编号
	private String parentName;//父模块名称
	private String name;//模块名
	private Integer layerNum;//层数
	private Integer isLeaf;//是否叶子
	private String Ico;//图片
	private String cpermission;//权限
	private String curl;//路径
	private Integer ctype;//菜单的类型:主菜单，左侧菜单，按钮
	private Integer state;//状态
	private String belong;//从属于
	private String cwhich;
	private Integer quoteNum;//应用次数
	private String remark;//备注
	private Integer orderNo;//排序号
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLayerNum() {
		return layerNum;
	}
	public void setLayerNum(Integer layerNum) {
		this.layerNum = layerNum;
	}
	public Integer getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getIco() {
		return Ico;
	}
	public void setIco(String ico) {
		Ico = ico;
	}
	public String getCpermission() {
		return cpermission;
	}
	public void setCpermission(String cpermission) {
		this.cpermission = cpermission;
	}
	public String getCurl() {
		return curl;
	}
	public void setCurl(String curl) {
		this.curl = curl;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getCwhich() {
		return cwhich;
	}
	public void setCwhich(String cwhich) {
		this.cwhich = cwhich;
	}
	public Integer getQuoteNum() {
		return quoteNum;
	}
	public void setQuoteNum(Integer quoteNum) {
		this.quoteNum = quoteNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
