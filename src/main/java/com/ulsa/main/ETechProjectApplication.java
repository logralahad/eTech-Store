package com.ulsa.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class ETechProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ETechProjectApplication.class, args);
		System.out.println("Tienda en línea y estable.");
	}
}
