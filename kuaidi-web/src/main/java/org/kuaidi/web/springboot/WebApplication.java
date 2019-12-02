package org.kuaidi.web.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages= {"org.kuaidi.web.springboot.*","org.kuaidi.web.springboot.adapter*"})
public class WebApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
}
