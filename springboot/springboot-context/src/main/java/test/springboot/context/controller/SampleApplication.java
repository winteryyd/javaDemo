package test.springboot.context.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "test.springboot")
public class SampleApplication{
	public static void main(String[] args) {
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		SpringApplication.run(SampleApplication.class, args);
		System.out.println("++++++++++++++++++++++++++++++++++++++");
	}
}
