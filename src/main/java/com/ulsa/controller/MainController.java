package com.ulsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String homePage() {
		System.out.println("Welcome");
		return "Home";
	}
	
}
