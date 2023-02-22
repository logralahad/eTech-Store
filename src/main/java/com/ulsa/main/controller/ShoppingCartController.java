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

import com.ulsa.main.entity.Product;
import com.ulsa.main.entity.Role;
import com.ulsa.main.entity.ShoppingCart;
import com.ulsa.main.entity.User;
import com.ulsa.main.entity.Wishlist;
import com.ulsa.main.repository.ProductRepository;
import com.ulsa.main.repository.ShoppingCartRepository;
import com.ulsa.main.repository.UserRepository;
import com.ulsa.main.repository.WishlistRepository;

@Controller
public class ShoppingCartController {

	private final ShoppingCartRepository shoppingCartRepository;

	@Autowired
	public ShoppingCartController(ShoppingCartRepository shoppingCartRepository) {
		super();
		this.shoppingCartRepository = shoppingCartRepository;
	}

	@GetMapping("/shopping-cart")
	public String viewCartPage(Model model) {
		System.out.println("Shopping Cart");
		float subtotal = 0;
		List<ShoppingCart> items = shoppingCartRepository.findByUser_Id("1e272911-df5c-4fc5-9fd4-71a08fd31390");
		for (ShoppingCart sp : items) {
			subtotal += sp.getQuantity() * sp.getProduct().getPrice();
		}
		model.addAttribute("items", items);
		model.addAttribute("subtotal", subtotal);
		return "pages/public/ShoppingCart";
	}

	@GetMapping("/shopping-cart/add/{productId}")
	public String addItemToShoppingCart(@PathVariable("productId") String id, Model model) {
		ShoppingCart item = shoppingCartRepository.findByUser_IdAndProduct_Id("1e272911-df5c-4fc5-9fd4-71a08fd31390",
				id);

		if (item != null) {
			item.setQuantity(item.getQuantity() + 1);
			shoppingCartRepository.save(item);
		} else {
			Product product = new Product();
			product.setId(id);

			User user = new User();
			user.setId("1e272911-df5c-4fc5-9fd4-71a08fd31390");

			ShoppingCart sp = new ShoppingCart();
			sp.setProduct(product);
			sp.setUser(user);
			sp.setQuantity(1);
			shoppingCartRepository.save(sp);
		}

		float subtotal = 0;
		List<ShoppingCart> items = shoppingCartRepository.findByUser_Id("1e272911-df5c-4fc5-9fd4-71a08fd31390");
		for (ShoppingCart sp : items) {
			subtotal += sp.getQuantity() * sp.getProduct().getPrice();
		}
		model.addAttribute("items", items);
		model.addAttribute("subtotal", subtotal);
		return "pages/public/ShoppingCart";
	}

	@GetMapping("/shopping-cart/delete/{productId}")
	public String deleteItemFromShoppingCart(@PathVariable("productId") String id, Model model) {
		ShoppingCart item = shoppingCartRepository.findByUser_IdAndProduct_Id("1e272911-df5c-4fc5-9fd4-71a08fd31390",
				id);

		if (item.getQuantity() == 1) {
			shoppingCartRepository.delete(item);
		} else {
			item.setQuantity(item.getQuantity() - 1);
			shoppingCartRepository.save(item);
		}

		float subtotal = 0;
		List<ShoppingCart> items = shoppingCartRepository.findByUser_Id("1e272911-df5c-4fc5-9fd4-71a08fd31390");
		for (ShoppingCart sp : items) {
			subtotal += sp.getQuantity() * sp.getProduct().getPrice();
		}
		model.addAttribute("items", items);
		model.addAttribute("subtotal", subtotal);
		return "pages/public/ShoppingCart";
	}

}
