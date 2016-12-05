package com.deppon.demo.quartz.test;

import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.demo.jdbc.session.Session;
import com.deppon.demo.quartz.entity.ScheduleJob;
import com.deppon.demo.quartz.scheduler.SchedulerFactory;

public class test {
	public static void main(String[] args) throws SchedulerException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:com/deppon/demo/quartz/spring.xml");
		
		StdScheduler scheduler = (StdScheduler) context
				.getBean("schedulerFactoryBean");
		
		SchedulerFactory schedulerFactory = new SchedulerFactory();
		schedulerFactory.setScheduler(scheduler);

		Session session = new Session();
		session.biuldConnection();
		
		ScheduleJob scheduleJob = session.get(ScheduleJob.class, "1");
		schedulerFactory.addJob(scheduleJob);
		schedulerFactory.startSchedule();
	}
}
