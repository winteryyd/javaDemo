package com.deppon.demo.java.DesignPatterns.Proxy.StaticProxy;

import com.deppon.demo.java.DesignPatterns.Proxy.Hello;
import com.deppon.demo.java.DesignPatterns.Proxy.HelloImpl;

public class HelloProxy implements Hello{

	private Hello hello;
	
	public HelloProxy(){
		hello = new HelloImpl();
	}
	@Override
	public void say(String name) {
		// TODO Auto-generated method stub
		before();
		hello.say(name);
		after();
	}
	
	private void before(){
		System.out.println("before");
	}

	private void after(){
		System.out.println("after");
	}
}
