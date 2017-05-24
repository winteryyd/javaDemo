package com.deppon.demo.java.DesignPatterns.Proxy.DynamicProxy;

import com.deppon.demo.java.DesignPatterns.Proxy.Hello;
import com.deppon.demo.java.DesignPatterns.Proxy.HelloImpl;

public class DynamicProxyTest {

	public static void main(String[] args) {
		DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
		Hello hello = dynamicProxy.getProxy();
		hello.say("Rose");
	}
}
