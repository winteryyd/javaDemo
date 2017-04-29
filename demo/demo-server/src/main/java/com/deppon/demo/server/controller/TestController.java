package com.deppon.demo.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deppon.demo.server.dao.impl.CrudDao;
import com.deppon.demo.server.services.ITestService;
import com.deppon.demo.shared.entity.TestEntity;

@Controller
@RequestMapping("/test")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private ITestService testService;
	
	@RequestMapping("/index")
	public String index(){
		testService.test();
		for(int i=1;i<1000;i++){
			TestEntity entity = new TestEntity();
			entity.setId(i+"");
			entity.setName("name"+i);
			entity.setBirth(new Date());
			entity.setAddr("上海");
			testService.insert(entity);
		}
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value = "listData")
	public List<TestEntity> listData() {
		List<TestEntity> list = new ArrayList<TestEntity>();
		/*Date start = new Date();
		for(int i=1;i<10000;i++){
			list.add(testService.get(TestEntity.class, i+""));
		}
		Date end = new Date();
		logger.info("{}",end.getTime()-start.getTime());*/
		TestEntity entity = testService.get(TestEntity.class, "1");
		list.add(entity);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "updateData")
	public String update() {
		TestEntity entity = testService.get(TestEntity.class, "1");
		entity.setBirth(new Date());		
		testService.update(entity);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "insertData")
	public List<TestEntity> insertData() {
		List<TestEntity> list = new ArrayList<TestEntity>();
		
		TestEntity entity = new TestEntity();
		entity.setId("1");
		entity.setName("name");
		entity.setBirth(new Date());
		entity.setAddr("青浦");
		testService.insert(entity);
		
		TestEntity entity1 = testService.get(TestEntity.class, "1");
		list.add(entity1);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteData")
	public List<TestEntity> deleteData() {
		List<TestEntity> list = new ArrayList<TestEntity>();
		
		TestEntity entity = testService.get(TestEntity.class, "1");
		testService.delete(entity);
		list.add(testService.get(TestEntity.class, "1"));
		return list;
	}
	
}
