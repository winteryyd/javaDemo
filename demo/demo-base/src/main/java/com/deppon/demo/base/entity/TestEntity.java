package com.deppon.demo.base.entity;

import java.util.Date;

import com.deppon.demo.base.annotation.CacheEntity;
//@CacheEntity(fieldsKey = {"id","name"},expire = 86400)
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
