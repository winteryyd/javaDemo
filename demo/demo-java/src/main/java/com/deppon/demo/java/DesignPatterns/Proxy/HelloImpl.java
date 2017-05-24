package com.deppon.demo.java.DesignPatterns.Proxy;

public class HelloImpl implements Hello{

	@Override
	public void say(String name) {
		System.out.println("Hello! "+name);
		System.out.println(this.getClass().getClassLoader());
		System.out.println(Thread.currentThread().getContextClassLoader());
	}
}
