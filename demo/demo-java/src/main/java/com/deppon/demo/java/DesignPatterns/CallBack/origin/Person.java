package com.deppon.demo.java.DesignPatterns.CallBack.origin;

public class Person implements CallBack {
	// 种下获得的种子
	public void plant(final Seed seed) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					seed.plantByPerson(Person.this);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		daily();
	}

	public void daily() {
		System.out.println("日常工作。。。。。。");
	}

	public void harvest() {
		System.out.println("收货粮食。。。。。。");
	}

	@Override
	public void call() {
		harvest();
	}

	@Override
	public <T> T Call() {
		harvest();
		return null;
	}

}
