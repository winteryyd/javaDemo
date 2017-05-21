package com.deppon.demo.java.DesignPatterns.Decorator.origin.condiment;

import com.deppon.demo.java.DesignPatterns.Decorator.origin.Coffee;
import com.deppon.demo.java.DesignPatterns.Decorator.origin.Condiment;

public class Chocolate extends Condiment{

	public Chocolate(Coffee cf) {
		super(cf);
	}

	@Override
	public String getCoffee() {
		return super.getCoffee() + " 加巧克力";
	}

	@Override
	public double price() {
		return super.price() + 1.2;
	}

}
