package com.deppon.demo.java.DesignPatterns.Decorator.origin.condiment;

import com.deppon.demo.java.DesignPatterns.Decorator.origin.Coffee;
import com.deppon.demo.java.DesignPatterns.Decorator.origin.Condiment;

public class Milk extends Condiment{

	public Milk(Coffee cf) {
		super(cf);
	}

	@Override
	public String getCoffee() {
		return super.getCoffee()+" 加牛奶";
	}

	@Override
	public double price() {
		return super.price()+0.88;
	}

}
