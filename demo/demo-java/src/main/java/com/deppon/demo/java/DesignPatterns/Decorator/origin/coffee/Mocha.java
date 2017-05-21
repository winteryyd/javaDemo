package com.deppon.demo.java.DesignPatterns.Decorator.origin.coffee;

import com.deppon.demo.java.DesignPatterns.Decorator.origin.Coffee;

//摩卡
public class Mocha implements Coffee{

	@Override
	public String getCoffee() {
		return "mocha";
	}

	@Override
	public double price() {
		return 1.88;
	}

}
