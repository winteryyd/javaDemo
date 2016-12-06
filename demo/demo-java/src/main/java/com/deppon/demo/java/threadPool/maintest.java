package com.deppon.demo.java.threadPool;

public class maintest {
	private static final ThreadPool tp = new DefaultThreadPool();
	public static void main(String[] args) throws InterruptedException {
		while(true){
			Thread.sleep((int)(1+Math.random()*10)*1000);
			tp.addJob((int)(1+Math.random()*10));
			System.out.println("now:"+tp.getWorkerNum()+"-->"+tp.getJobSize());
		}
	}
}
