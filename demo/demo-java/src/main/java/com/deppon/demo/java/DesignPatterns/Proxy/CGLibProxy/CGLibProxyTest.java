package com.deppon.demo.java.DesignPatterns.Proxy.CGLibProxy;

import com.deppon.demo.java.DesignPatterns.Proxy.Hello;
import com.deppon.demo.java.DesignPatterns.Proxy.HelloImpl;

public class CGLibProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CGLibProxy cgLibProxy = new CGLibProxy();
		Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
		helloProxy.say("Rose");

	}

}
