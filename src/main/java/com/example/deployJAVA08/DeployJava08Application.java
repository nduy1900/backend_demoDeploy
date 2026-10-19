package com.example.deployJAVA08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DeployJava08Application {

	public static void main(String[] args) {
		SpringApplication.run(DeployJava08Application.class, args);
	}

}
