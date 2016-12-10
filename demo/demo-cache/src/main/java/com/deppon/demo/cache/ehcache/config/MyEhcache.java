package com.deppon.demo.cache.ehcache.config;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class MyEhcache {
	public static void main(String[] args) {
		EhcacheBean bean1 = new EhcacheBean(1, "aaa", 2);
		CacheManager manager = CacheManager.create("/D:/GitHub/javaDemo/demo/demo-cache/src/main/java/com/deppon/demo/cache/ehcache/config/ehcache-local.xml");
		Cache demo = manager.getCache("demoCache");
		demo.put(new Element(bean1.getId(), bean1));
		Element e = demo.get(bean1.getId());
		System.out.println(((EhcacheBean) e.getValue()).getName());
	}
}

class EhcacheBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int age;

	public EhcacheBean(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}