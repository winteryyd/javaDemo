package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class HelloApplication {
	@RequestMapping(method=RequestMethod.GET,path="/hello")
	public String hello(){
		return "hello world!";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/www")
	public String www(){
		return "hello world! www";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/www1")
	public String www1(){
		return "hello world! www";
	}
}
