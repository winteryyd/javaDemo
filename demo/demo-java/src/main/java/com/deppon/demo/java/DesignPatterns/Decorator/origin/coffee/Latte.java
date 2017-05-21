package com.deppon.demo.java.DesignPatterns.Decorator.origin.coffee;

import com.deppon.demo.java.DesignPatterns.Decorator.origin.Coffee;

//拿铁
public class Latte implements Coffee{

	@Override
	public String getCoffee() {
		return "latte";
	}

	@Override
	public double price() {
		return 2.88;
	}

}
