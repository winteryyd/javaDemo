package com.deppon.demo.shared.entity;

import java.util.Date;

import com.deppon.demo.jdbc.persistence.entity.BaseEntity;

public class TestEntity extends BaseEntity<TestEntity>{
	private static final long serialVersionUID = 1L;
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
}
