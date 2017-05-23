package com.deppon.demo.java.DesignPatterns.Factory.origin.factoryMethod;

import com.deppon.demo.java.DesignPatterns.Factory.origin.BMW320;
import com.deppon.demo.java.DesignPatterns.Factory.origin.BMW523;

public class FactoryMethodTest {

	public static void main(String[] args) {
		FactoryBMW320 factoryBMW320 = new FactoryBMW320();  
        BMW320 bmw320 = factoryBMW320.createBMW();  
  
        FactoryBMW523 factoryBMW523 = new FactoryBMW523();  
        BMW523 bmw523 = factoryBMW523.createBMW();  
	}

}
