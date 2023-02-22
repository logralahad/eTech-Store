package com.ulsa.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ulsa.main.entity.User;

@Controller
public class RoutesController {

	@GetMapping("/")
	public String viewHomePage() {
		System.out.println("Welcome");
		return "pages/public/Home";
	}

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
	
	@GetMapping("/dashboard/roles")
	public String viewRolesPage(Model model) {
		System.out.println("Roles");
		return "pages/private/Rol/Roles";
	}
	
	@GetMapping("/dashboard/usuarios")
	public String viewUsuariosPage(Model model) {
		System.out.println("Usuarios");
		return "pages/private/Usuario/Usuarios";
	}
	
	@GetMapping("/dashboard/productos")
	public String viewProductosPage(Model model) {
		System.out.println("Productos");
		return "pages/private/Producto/Productos";
	}
	
	@GetMapping("/dashboard/ordenes")
	public String viewOrdenesPage(Model model) {
		System.out.println("Ordenes");
		return "pages/private/Orden/Ordenes";
	}	
	
	@GetMapping("/dashboard/metodos-pago") 
	public String viewPagosPage(Model model) {
		System.out.println("Métodos de pago");
		return "pages/private/Pagos/MetodosDePago";
	}

}
