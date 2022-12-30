package com.ankhang;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@ComponentScan(basePackages = "com.*")
@EnableWebSecurity
//@EnableAutoConfiguration(exclude = { //  
//        DataSourceAutoConfiguration.class, //
//        DataSourceTransactionManagerAutoConfiguration.class, //
//        HibernateJpaAutoConfiguration.class })
@EnableAutoConfiguration(exclude = { //  
		 HibernateJpaAutoConfiguration.class
       })
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true )
public class ProjectEntityManagerApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectEntityManagerApplication.class, args);

		
		
		
	}
	


}
