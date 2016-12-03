package com.deppon.demo.jdbc;

import java.util.Date;

public class UserInfo {
	private int id;
	private String name;
	private String pwd;
	private Date age;

	public Date getAge() {
		return age;
	}

	public void setAge(Date age) {
		this.age = age;
	}

	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", pwd=" + pwd
				+ ", age=" + age + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}