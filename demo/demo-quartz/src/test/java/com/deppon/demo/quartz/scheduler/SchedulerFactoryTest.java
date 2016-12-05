package com.deppon.demo.quartz.scheduler;

import org.junit.Before;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.demo.jdbc.session.Session;

public class SchedulerFactoryTest {
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath:com/deppon/demo/quartz/spring.xml");
	SchedulerFactory schedulerFactory = new SchedulerFactory();

	public Session session;
	@Before
	public void init() {
		StdScheduler scheduler = (StdScheduler) context.getBean("schedulerFactoryBean");
		schedulerFactory.setScheduler(scheduler);
		session=new Session();
		session.biuldConnection();
		
	}

}
