package com.deppon.demo.java.DesignPatterns.Factory.origin.factoryMethod;

import com.deppon.demo.java.DesignPatterns.Factory.origin.BMW320;

public class FactoryBMW320 implements FactoryBMW{  
	  
    @Override  
    public BMW320 createBMW() {  
        return new BMW320();  
    }  
  
}  