package com.deppon.demo.java.DesignPatterns.Proxy.StaticProxy;

import com.deppon.demo.java.DesignPatterns.Proxy.Hello;

public class StaticProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hello hello = new HelloProxy();
		hello.say("rose");
	}

}
