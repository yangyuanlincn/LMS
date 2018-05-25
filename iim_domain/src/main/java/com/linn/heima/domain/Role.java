package com.linn.heima.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role extends BaseEntity implements Serializable {
	private String id;
	private String name;
	private String remark;
	private Integer orderNo;
	private Set<User> users = new HashSet<User>(0);//角色与用户多对多
	private Set<Module> modules = new HashSet<Module>(0);//角色与模块多对多
	
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
