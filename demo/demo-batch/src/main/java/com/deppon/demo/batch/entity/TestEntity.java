package com.deppon.demo.batch.entity;

import java.util.Date;

public class TestEntity{
	private static final long serialVersionUID = 1L;
	/**
	 * 实体编号（唯一标识）
	 */
	private String id;
	private String name;
	private Date birth;
	private String addr;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
