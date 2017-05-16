/*package com.deppon.demo.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deppon.demo.batch.service.ICachePushService;

@Controller
@RequestMapping("/batch")
public class BatchController {
	private static final Logger logger = LoggerFactory.getLogger(BatchController.class);
	@Autowired
	private ICachePushService cachePushService;
	
	@RequestMapping("/index")
	public String index(){
		cachePushService.pushCache();
		return "index";
	}
	
}
*/