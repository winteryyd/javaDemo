package com.deppon.demo.java.DesignPatterns.Decorator.origin;

import com.deppon.demo.java.DesignPatterns.Decorator.origin.coffee.Mocha;
import com.deppon.demo.java.DesignPatterns.Decorator.origin.condiment.Chocolate;
import com.deppon.demo.java.DesignPatterns.Decorator.origin.condiment.Milk;


public class Test {
	
	public static void main(String[] args) {
		Coffee coffee = new Mocha();
		System.out.println(coffee.getCoffee()+"  $"+coffee.price());
		coffee = new Milk(coffee);
		System.out.println(coffee.getCoffee()+"  $"+coffee.price());
		coffee = new Chocolate(coffee);
		System.out.println(coffee.getCoffee()+"  $"+coffee.price());
	}

}
