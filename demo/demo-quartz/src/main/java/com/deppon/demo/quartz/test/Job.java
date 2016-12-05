package com.deppon.demo.quartz.test;

import java.util.Date;

public class Job {
	public void runTest(){
		System.out.println(new Date().toLocaleString()+"   调度执行。。。");
	}
}
