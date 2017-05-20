package com.deppon.demo.java.DesignPatterns.Observer.spring;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyEvent(Object source) {
		super(source);
		System.out.println("my Event");
	}

	public void print() {
		System.out.println("hello spring event[MyEvent]");
	}
}