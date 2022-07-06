package com.ankhang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@ComponentScan(basePackages = "com.*")
public class ProjectEntityManagerApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectEntityManagerApplication.class, args);
	}

}
