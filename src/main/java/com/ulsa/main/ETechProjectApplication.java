package com.ulsa.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.ulsa.entity")
public class ETechProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ETechProjectApplication.class, args);
		System.out.println("Tienda en línea y estable.");
	}

}
