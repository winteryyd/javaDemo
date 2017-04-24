package com.deppon.demo.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deppon.demo.server.services.ITestService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private ITestService testService;
	
	@RequestMapping("/index")
	public String index(){
		System.out.println("==============");
		System.out.println(testService.get("1"));
		return "index";
	}
}
