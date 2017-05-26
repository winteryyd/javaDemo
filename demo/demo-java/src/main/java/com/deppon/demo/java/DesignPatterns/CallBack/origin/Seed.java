package com.deppon.demo.java.DesignPatterns.CallBack.origin;

public class Seed {
	
	public void plantByPerson(CallBack person) throws InterruptedException {
		System.out.println("我被种下了！");
		System.out.println("发芽成长。。。。。。");
		Thread.sleep(10000); 
		System.out.println("成熟。。。。。。");
		person.call();
	}

}
