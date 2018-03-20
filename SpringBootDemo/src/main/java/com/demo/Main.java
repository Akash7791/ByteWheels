package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={"com.demo.controller","com.demo.dao","com.demo.daoImpl","com.demo.service",
		"com.demo.serviceImpl","com.demo.model"})
@EntityScan({"com.demo.model"})
@ComponentScan({"com.demo.controller","com.demo.dao","com.demo.daoImpl","com.demo.model","com.demo.service","com.demo.serviceImpl"})
public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
	