package com.deppon.demo.java.DesignPatterns.Observer.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:com/deppon/demo/java/DesignPatterns/Observer/spring/spring.xml");
        MyPubisher myPubisher=(MyPubisher) context.getBean("myPublisher");
        myPubisher.publishEvent(new MyEvent("1"));
    }
}