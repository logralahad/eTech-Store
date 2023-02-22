package com.ulsa.main.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ulsa.main.entity.Category;
import com.ulsa.main.entity.Color;
import com.ulsa.main.entity.Product;
import com.ulsa.main.repository.CategoryRepository;
import com.ulsa.main.repository.ColorRepository;
import com.ulsa.main.repository.ProductRepository;

@Controller
public class ProductController {

	private final ProductRepository productRepository;
	private final ColorRepository colorRepository;
	private final CategoryRepository categoryRepository;
	private final String mainPage = "pages/private/Producto/Productos";
	private final String addPage = "pages/private/Producto/AddProduct";
	private final String editPage = "pages/private/Producto/EditProduct";

	@Autowired
	public ProductController(ProductRepository productRepository, ColorRepository colorRepository,
			CategoryRepository categoryRepository) {
		super();
		this.productRepository = productRepository;
		this.colorRepository = colorRepository;
		this.categoryRepository = categoryRepository;
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
		model.addAttribute("colors", colorRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());
		return this.addPage;
	}

	@GetMapping("/product/edit/{id}")
	public String editProduct(@PathVariable("id") String id, Model model) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		System.out.println(product.getCategories().toString());
		model.addAttribute("editProduct", product);
		model.addAttribute("catalogCol", colorRepository.findAll());
		model.addAttribute("catalagoCat", categoryRepository.findAll());
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
	public String addProduct(@Validated Product product, @RequestParam List<Long> categorias,
			@RequestParam List<Long> colores, BindingResult result, Model model) {		
		List<Category> categories = categoryRepository.findAllById(categorias);
		List<Color> colors = colorRepository.findAllById(colores);
		
		for (Category category : categories) {
			category.getProducts().add(product);
			product.getCategories().add(category);
		}
		
		for (Color color : colors) {
			color.getProducts().add(product);
			product.getColors().add(color);
		}
		
		product.setCreated_at(new Date());
		productRepository.save(product);
		model.addAttribute("products", productRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/product/update/{id}")
	public String updateProduct(@PathVariable("id") String id, @Validated Product product, BindingResult result,
			Model model) {
		productRepository.save(product);
		model.addAttribute("products", productRepository.findAll());
		return this.mainPage;
	}

}
