package com.ulsa.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ulsa.main.entity.Order;
import com.ulsa.main.repository.OrderRepository;


@Controller
public class OrderController {

	private final OrderRepository orderRepository;
	private final String mainPage = "pages/private/Orden/Ordenes";
	private final String addPage = "pages/private/Orden/AddOrder";
	private final String editPage = "pages/private/Orden/EditOrder";

	@Autowired
	public OrderController(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	@GetMapping("/dashboard/ordenes")
	public String viewOrdernesPage(Model model) {
		System.out.println("Ordernes");
		model.addAttribute("orders", orderRepository.findAll());
		return this.mainPage;
	}

	@GetMapping("/dashboard/orderes/agregar")
	public String viewAgregarOrderPage(Model model) {
		System.out.println("Agregar order");
		model.addAttribute("newOrder", new Order());
		return this.addPage;
	}
	
	@GetMapping("/order/edit/{id}")
	public String editOrder(@PathVariable("id") long id, Model model) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		model.addAttribute("editOrder", order);
		return this.editPage;
	}

	@GetMapping("/order/delete/{id}")
	public String deleteOrder(@PathVariable("id") long id, Model model) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		orderRepository.delete(order);
		model.addAttribute("orders", orderRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/order/add")
	public String addOrder(@Validated Order order, BindingResult result, Model model) {
		orderRepository.save(order);
		model.addAttribute("orders", orderRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/order/update/{id}")
	public String updateOrder(@PathVariable("id") long id, @Validated Order order, BindingResult result, Model model) {
		orderRepository.save(order);
		model.addAttribute("orders", orderRepository.findAll());
		return this.mainPage;
	}

}
