package com.deppon.demo.java.DesignPatterns.Proxy.CGLibProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


public class CGLibProxy implements MethodInterceptor{

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls){
		return (T) Enhancer.create(cls, this);
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		before();
		Object result = proxy.invokeSuper(obj, args);
		after();
		return result;
	}

	private void before(){
		System.out.println("before");
	}

	private void after(){
		System.out.println("after");
	}
}
