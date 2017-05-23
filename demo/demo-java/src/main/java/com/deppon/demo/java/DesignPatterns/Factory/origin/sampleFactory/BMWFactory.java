package com.deppon.demo.java.DesignPatterns.Factory.origin.sampleFactory;

import com.deppon.demo.java.DesignPatterns.Factory.origin.BMW;
import com.deppon.demo.java.DesignPatterns.Factory.origin.BMW320;
import com.deppon.demo.java.DesignPatterns.Factory.origin.BMW523;

public class BMWFactory {
	public BMW createBMW(int type) {
		switch (type) {

		case 320:
			return new BMW320();

		case 523:
			return new BMW523();

		default:
			break;
		}
		return null;
	}
}
