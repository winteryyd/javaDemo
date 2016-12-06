package com.deppon.demo.java.threadPool;

public class Job{
	private String name;
	public Job(String name) {
		this.name=name;
	}

	public void run(String tname) {
		try {
			Thread.sleep((int)(1+Math.random()*10)*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("                                线程:"+tname+" do "+name);
	   }
}
