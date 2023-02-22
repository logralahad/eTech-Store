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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ulsa.main.entity.Order;
import com.ulsa.main.entity.OrderProducts;
import com.ulsa.main.entity.Payment;
import com.ulsa.main.entity.ShipmentAddress;
import com.ulsa.main.entity.ShoppingCart;
import com.ulsa.main.repository.OrderProductsRepository;
import com.ulsa.main.repository.OrderRepository;
import com.ulsa.main.repository.PaymentRepository;
import com.ulsa.main.repository.ShippingAddressRepository;
import com.ulsa.main.repository.ShoppingCartRepository;

@Controller
public class OrderController {

	private final OrderRepository orderRepository;
	private final ShoppingCartRepository shoppingCartRepository;
	private final ShippingAddressRepository shippingRepository;
	private final PaymentRepository paymentRepository;
	private final OrderProductsRepository orderProductRepository;

	private final String mainPage = "pages/private/Orden/Ordenes";

	@Autowired
	public OrderController(OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository,
			ShippingAddressRepository shippingRepository, PaymentRepository paymentRepository,
			OrderProductsRepository orderProductRepository) {
		super();
		this.orderRepository = orderRepository;
		this.shoppingCartRepository = shoppingCartRepository;
		this.shippingRepository = shippingRepository;
		this.paymentRepository = paymentRepository;
		this.orderProductRepository = orderProductRepository;
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
		System.out.println("Ordenes");
		model.addAttribute("orders", orderRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/order/add")
	public String addOrder(@Validated ShipmentAddress ship, @RequestParam String shipping, @RequestParam String payment,
			@RequestParam String totalOrder,BindingResult result, Model model) {
		System.out.println(totalOrder);
		shippingRepository.save(ship);

		Payment paymentT = new Payment();
		paymentT.setProvider(payment);
		paymentT.setTransaction_id("ULSA-2023");
		paymentRepository.save(paymentT);

		Order order = new Order();
		order.setShipment_type(shipping);
		order.setShipment_address(ship);
		order.setPayment(paymentT);
		order.setTotal(Float.parseFloat(totalOrder));
		orderRepository.save(order);

		Set<OrderProducts> ops = new HashSet<OrderProducts>();
		List<ShoppingCart> items = shoppingCartRepository.findByUser_Id("1e272911-df5c-4fc5-9fd4-71a08fd31390");
		for (ShoppingCart sp : items) {
			OrderProducts op = new OrderProducts();
			op.setOrder(order);
			op.setProduct(sp.getProduct());
			ops.add(op);
		}
		orderProductRepository.saveAll(ops);

		model.addAttribute("orders", orderRepository.findAll());
		return this.mainPage;
	}

}
