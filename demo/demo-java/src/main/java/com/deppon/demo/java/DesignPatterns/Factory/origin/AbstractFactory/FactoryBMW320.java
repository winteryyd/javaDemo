package com.deppon.demo.java.DesignPatterns.Factory.origin.AbstractFactory;

//为宝马320系列生产配件    
public class FactoryBMW320 implements AbstractFactory {

	@Override
	public Engine createEngine() {
		return new EngineA();
	}

	@Override
	public Aircondition createAircondition() {
		return new AirconditionA();
	}
}