package com.deppon.demo.java.DesignPatterns.Decorator.origin;

//调料基类
public abstract class Condiment implements Coffee{
	Coffee cf;
	public Condiment(Coffee cf){
		this.cf = cf;
	}

	public String getCoffee(){
		return cf.getCoffee();
	}

	public double price(){
		return cf.price();
	}
}
