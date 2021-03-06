package com.grsdev.greetings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class GreetingsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingsServiceApplication.class, args);
	}
}

@RestController
@RequestMapping(path="/")
@RefreshScope
class ReservationRestController{
	
	@Value("${greetings.service.message}")
	private String message;
	
	@Value("{server.port}")
	private String port;
	
	@Autowired
	private Environment env;
	
	@GetMapping
	public String getGreeting(){
		return message + " : "+ env.getProperty("server.port");
	}
	
}

