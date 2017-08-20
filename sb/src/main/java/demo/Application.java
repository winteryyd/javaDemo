package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "demo")
public class Application{

    public static void main(String[] args) {
    	
    	System.out.println("++++++++++++++++++++++++++++++++++++++");
        SpringApplication.run(applicationClass, args);
    	System.out.println("++++++++++++++++++++++++++++++++++++++");
    }

    private static Class<Application> applicationClass = Application.class;
}