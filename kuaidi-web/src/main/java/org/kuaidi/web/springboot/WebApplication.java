package org.kuaidi.web.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableScheduling
@ComponentScan(basePackages= {"org.kuaidi.web.springboot.*","org.kuaidi.web.springboot.adapter*"})
public class WebApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
