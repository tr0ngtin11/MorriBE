package com.sample.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


//	@Bean
//	NewTopic notifications() {
//		return new NewTopic("notifications", 2, (short) 1);
//	}
//
//	@Bean
//	NewTopic statistics() {
//		return new NewTopic("statistics", 1, (short) 1);
//	}
}
