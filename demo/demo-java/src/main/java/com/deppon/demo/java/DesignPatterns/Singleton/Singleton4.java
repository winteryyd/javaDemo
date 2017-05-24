package com.deppon.demo.java.DesignPatterns.Singleton;

public class Singleton4 {
	private Singleton4() {

	}

	private static class SingletonHolder {
		private final static Singleton4 instance = new Singleton4();
	}

	public static Singleton4 getInstance() {
		return SingletonHolder.instance;
	}
}