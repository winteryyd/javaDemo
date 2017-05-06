package com.deppon.demo.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deppon.demo.server.service.UserService;
import com.deppon.demo.shared.entity.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index(){
		UserEntity entity = userService.get("1");
		System.out.println(entity);
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value = "insert")
	public String insert() {
		UserEntity u = new UserEntity();
		u.setId("1");
		u.setName("张三");
		u.setPhone("110");
		u.setAddr("上海");
		userService.insert(u);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "update")
	public String update() {
		UserEntity entity = userService.get("1");
		entity.setName("李四");		
		userService.update(entity);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete() {
		UserEntity entity = userService.get("1");
		userService.delete(entity);
		return "success";
	}
	
}
