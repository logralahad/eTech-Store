package com.ulsa.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ulsa.main.entity.Category;
import com.ulsa.main.repository.CategoryRepository;

@Controller
public class CategoryController {

	private final CategoryRepository categoryRepository;
	private final String mainPage = "pages/private/Categoria/Categorias";
	private final String addPage = "pages/private/Categoria/AddCategory";
	private final String editPage = "pages/private/Categoria/EditCategory";

	@Autowired
	public CategoryController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/dashboard/categorias")
	public String viewCategoriasPage(Model model) {
		System.out.println("Categorias");
		model.addAttribute("categories", categoryRepository.findAll());
		return this.mainPage;
	}

	@GetMapping("/dashboard/categorias/agregar")
	public String viewAgregarCategoryPage(Model model) {
		System.out.println("Agregar category");
		model.addAttribute("newCategory", new Category());
		return this.addPage;
	}
	
	@GetMapping("/category/edit/{id}")
	public String editCategory(@PathVariable("id") long id, Model model) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		model.addAttribute("editCategory", category);
		return this.editPage;
	}

	@GetMapping("/category/delete/{id}")
	public String deleteCategory(@PathVariable("id") long id, Model model) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		categoryRepository.delete(category);
		model.addAttribute("categories", categoryRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/category/add")
	public String addCategory(@Validated Category category, BindingResult result, Model model) {
		categoryRepository.save(category);
		model.addAttribute("categories", categoryRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/category/update/{id}")
	public String updateCategory(@PathVariable("id") long id, @Validated Category category, BindingResult result, Model model) {
		categoryRepository.save(category);
		model.addAttribute("categories", categoryRepository.findAll());
		return this.mainPage;
	}

}
