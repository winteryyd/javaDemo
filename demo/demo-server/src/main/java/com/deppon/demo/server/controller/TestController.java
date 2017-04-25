package com.deppon.demo.server.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deppon.demo.server.services.ITestService;
import com.deppon.demo.shared.entity.TestEntity;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private ITestService testService;
	
	@RequestMapping("/index")
	public String index(){
		System.out.println("==============");
		testService.test();
		TestEntity entity = new TestEntity();
		entity.setId("1111");
		entity.setName("zhangsan");
		entity.setBirth(new Date());
		entity.setAddr("上海");
		testService.insert(entity);
		testService.delete(entity);
		entity.setId("222");
		testService.insert(entity);
		
		TestEntity et = testService.get(TestEntity.class,"222");
		et.setName("youdianlei");
		testService.update(et);
		return "index";
	}
}
