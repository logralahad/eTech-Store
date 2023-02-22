package com.ulsa.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ulsa.main.entity.User;

@Controller
public class RoutesController {

	@GetMapping("/login")
	public String viewLoginPage() {
		System.out.println("Sign In");
		return "pages/public/Login";
	}

	@GetMapping("/register")
	public String viewRegisterPage(Model model) {
		System.out.println("Sign Up");
		model.addAttribute("newUser", new User());
		return "pages/public/Register";
	}
	
	@GetMapping("/dashboard")
	public String viewDashboardPage(Model model) {
		System.out.println("Dashboard");
		return "pages/private/Dashboard";
	}

}
