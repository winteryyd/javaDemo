package com.deppon.demo.server.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deppon.demo.batch.BatchExecutor;

@Controller
@RequestMapping("/batch")
public class CopyOfTestController {
	private static final Logger logger = LoggerFactory.getLogger(CopyOfTestController.class);
	@Resource(name = "cachePushServiceImpl")
	private BatchExecutor batchExecutor;
	
	@RequestMapping("/index")
	public String index(){
		batchExecutor.execute();
		return "index";
	}
	
}
