package com.ulsa.main.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ulsa.main.entity.Order;
import com.ulsa.main.entity.OrderProducts;
import com.ulsa.main.entity.Payment;
import com.ulsa.main.entity.ShipmentAddress;
import com.ulsa.main.entity.ShoppingCart;
import com.ulsa.main.repository.OrderRepository;
import com.ulsa.main.repository.ShoppingCartRepository;


@Controller
public class OrderController {

	private final OrderRepository orderRepository;
	private final ShoppingCartRepository shoppingCartRepository;
	
	private final String mainPage = "pages/private/Orden/Ordenes";
	private final String addPage = "pages/private/Orden/AddOrder";
	private final String editPage = "pages/private/Orden/EditOrder";

	@Autowired
	public OrderController(OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository) {
		super();
		this.orderRepository = orderRepository;
		this.shoppingCartRepository = shoppingCartRepository;
	}
	
	@GetMapping("/checkout")
	public String viewCheckoutPage(Model model) {
		System.out.println("Checkout");
		float subtotal = 0;
		List<ShoppingCart> items = shoppingCartRepository.findByUser_Id("1e272911-df5c-4fc5-9fd4-71a08fd31390");
		for (ShoppingCart sp : items) {
			subtotal += sp.getQuantity() * sp.getProduct().getPrice();
		}
		
		model.addAttribute("items", items);
		model.addAttribute("subtotal", subtotal);
		model.addAttribute("newAddress", new ShipmentAddress());
		return "pages/public/Checkout"; 
	}

	@GetMapping("/dashboard/ordenes")
	public String viewOrdernesPage(Model model) {
		System.out.println("Ordernes");
		model.addAttribute("orders", orderRepository.findAll());
		return this.mainPage;
	}


	@PostMapping("/order/add")
	public String addOrder(@Validated ShipmentAddress ship,@RequestParam String shipping,
			@RequestParam String payment, BindingResult result, Model model) {
		Payment paymentT = new Payment();
		paymentT.setProvider(payment);
		paymentT.setTransaction_id("ULSA-2023");
		
		Order order = new Order();
		order.setShipment_type(shipping);
		order.setShipment_address(ship);
		order.setPayment(paymentT);
		orderRepository.save(order);

		Set<OrderProducts> ops = new HashSet<OrderProducts>();
		List<ShoppingCart> items = shoppingCartRepository.findByUser_Id("1e272911-df5c-4fc5-9fd4-71a08fd31390");
		for (ShoppingCart sp : items) {
			OrderProducts op = new OrderProducts();
			op.setOrder(order);
			op.setProduct(sp.getProduct());
			ops.add(op);
		}
		
		model.addAttribute("orders", orderRepository.findAll());
		return this.mainPage;
	}

}
