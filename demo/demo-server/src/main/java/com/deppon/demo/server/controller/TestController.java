package com.deppon.demo.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deppon.demo.server.services.ITestService;
import com.deppon.demo.shared.entity.TestEntity;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private ITestService testService;
	
	@RequestMapping("/index")
	public String index(){
		/*testService.test();
		for(int i=1;i<10000;i++){
			TestEntity entity = new TestEntity();
			entity.setId(i+"");
			entity.setName("name"+i);
			entity.setBirth(new Date());
			entity.setAddr("上海");
			testService.insert(entity);
		}*/
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<TestEntity> listData() {
		List<TestEntity> list = new ArrayList<TestEntity>();
		
		/*for(int i=1;i<100;i++){
			list.add(testService.get(TestEntity.class, i+""));
		}*/
		TestEntity entity = testService.get(TestEntity.class, "1");
		list.add(entity);
		entity.setBirth(new Date());
		
		testService.update(entity);
		return list;
	}
	
}
