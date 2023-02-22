package com.ulsa.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ulsa.main.entity.Product;
import com.ulsa.main.repository.ProductRepository;

@Controller
public class ProductController {

	private final ProductRepository productRepository;
	private final String mainPage = "pages/private/Producto/Productos";
	private final String addPage = "pages/private/Producto/AddProduct";
	private final String editPage = "pages/private/Producto/EditProduct";

	@Autowired
	public ProductController(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@GetMapping("/dashboard/productos")
	public String viewProductosPage(Model model) {
		System.out.println("Productos");
		model.addAttribute("products", productRepository.findAll());
		return this.mainPage;
	}

	@GetMapping("/dashboard/productos/agregar")
	public String viewAgregarProductPage(Model model) {
		System.out.println("Agregar product");
		model.addAttribute("newProduct", new Product());
		return this.addPage;
	}
	
	@GetMapping("/product/edit/{id}")
	public String editProduct(@PathVariable("id") String id, Model model) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		model.addAttribute("editProduct", product);
		return this.editPage;
	}

	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") String id, Model model) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		productRepository.delete(product);
		model.addAttribute("products", productRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/product/add")
	public String addProduct(@Validated Product product, BindingResult result, Model model) {
		productRepository.save(product);
		model.addAttribute("products", productRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/product/update/{id}")
	public String updateProduct(@PathVariable("id") long id, @Validated Product product, BindingResult result, Model model) {
		productRepository.save(product);
		model.addAttribute("products", productRepository.findAll());
		return this.mainPage;
	}

}
