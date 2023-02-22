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
import com.ulsa.main.entity.User;
import com.ulsa.main.entity.Wishlist;
import com.ulsa.main.repository.ProductRepository;
import com.ulsa.main.repository.UserRepository;
import com.ulsa.main.repository.WishlistRepository;

@Controller
public class WishlistController {
	
	private final WishlistRepository wishRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public WishlistController(WishlistRepository wishRepository, ProductRepository productRepository, UserRepository userRepository) {
		super();
		this.wishRepository = wishRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		System.out.println("Welcome");
		model.addAttribute("products", productRepository.findAll());
		return "pages/public/Home";
	}
	
	@GetMapping("/wishlist")
	public String viewWishlistPage(Model model) {
		System.out.println("Wishlist");
		
		Set<String> ids = new HashSet<String>();
		List<Wishlist> items = wishRepository.findByUser_Id("1e272911-df5c-4fc5-9fd4-71a08fd31390");
		for (Wishlist wishlist : items) {
			ids.add(wishlist.getProduct().getId());
		}
		
		model.addAttribute("items", productRepository.findAllById(ids));
		model.addAttribute("sps", items);
		return "pages/public/Wishlist";
	}
	
	@GetMapping("/wishlist/add/{productId}")
	public String addItemToWishlist(@PathVariable("productId") String id, Model model) {
		Product product = new Product();
		product.setId(id);
		
		User user = new User();
		user.setId("1e272911-df5c-4fc5-9fd4-71a08fd31390");
		
		Wishlist item = new Wishlist();
		item.setProduct(product);
		item.setUser(user);
		wishRepository.save(item);
		
		model.addAttribute("products", productRepository.findAll());
		return "pages/public/Home";
	}
	
	@GetMapping("/wishlist/delete/{productId}")
	public String deleteItemFromWishlist(@PathVariable("productId") String id, Model model) {
		Wishlist item = wishRepository.findByUser_IdAndProduct_Id("1e272911-df5c-4fc5-9fd4-71a08fd31390", id);
		wishRepository.delete(item);
		
		model.addAttribute("products", productRepository.findAll());
		return "pages/public/Home";
	}

}
