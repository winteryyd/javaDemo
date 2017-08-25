package test.springboot.context.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class HelloApplication {
	@RequestMapping(name = "HelloService",method=RequestMethod.GET,path="/hello")
	public String hello(){
		return "Hello";
	}
	
	@RequestMapping(name = "WorldService",method=RequestMethod.GET,path={"/world"})
	public String world(){
		return "World";
	}
	
	@RequestMapping(name = "FineService",method=RequestMethod.GET,path="/iam/fine")
	public String fine(){
		return "I am fine!";
	}
}
