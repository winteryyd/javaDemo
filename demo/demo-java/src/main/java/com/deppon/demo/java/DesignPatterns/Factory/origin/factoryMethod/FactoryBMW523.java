package com.deppon.demo.java.DesignPatterns.Factory.origin.factoryMethod;

import com.deppon.demo.java.DesignPatterns.Factory.origin.BMW523;

public class FactoryBMW523 implements FactoryBMW {  
    @Override  
    public BMW523 createBMW() {  
        return new BMW523();  
    }  
}  