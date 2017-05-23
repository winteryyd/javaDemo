package com.deppon.demo.java.DesignPatterns.Factory.origin.sampleFactory;

import com.deppon.demo.java.DesignPatterns.Factory.origin.BMW;

public class SampleFactoryTest {

	public static void main(String[] args) {
		BMWFactory factory = new BMWFactory();
		BMW bmw320 = factory.createBMW(320);
		BMW bmw523 = factory.createBMW(523);
	}

}
