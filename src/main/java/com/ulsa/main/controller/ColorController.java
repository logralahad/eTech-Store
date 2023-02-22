package com.ulsa.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ulsa.main.entity.Color;
import com.ulsa.main.repository.ColorRepository;

@Controller
public class ColorController {

	private final ColorRepository colorRepository;
	private final String mainPage = "pages/private/Color/Colores";
	private final String addPage = "pages/private/Color/AddColor";
	private final String editPage = "pages/private/Color/EditColor";

	@Autowired
	public ColorController(ColorRepository colorRepository) {
		super();
		this.colorRepository = colorRepository;
	}

	@GetMapping("/dashboard/colores")
	public String viewColoresPage(Model model) {
		System.out.println("Colores");
		model.addAttribute("colors", colorRepository.findAll());
		return this.mainPage;
	}

	@GetMapping("/dashboard/colores/agregar")
	public String viewAgregarColorPage(Model model) {
		System.out.println("Agregar color");
		model.addAttribute("newColor", new Color());
		return this.addPage;
	}
	
	@GetMapping("/color/edit/{id}")
	public String editColor(@PathVariable("id") long id, Model model) {
		Color color = colorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		model.addAttribute("editColor", color);
		return this.editPage;
	}

	@GetMapping("/color/delete/{id}")
	public String deleteColor(@PathVariable("id") long id, Model model) {
		Color color = colorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		colorRepository.delete(color);
		model.addAttribute("colors", colorRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/color/add")
	public String addColor(@Validated Color color, BindingResult result, Model model) {
		colorRepository.save(color);
		model.addAttribute("colors", colorRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/color/update/{id}")
	public String updateColor(@PathVariable("id") long id, @Validated Color color, BindingResult result, Model model) {
		colorRepository.save(color);
		model.addAttribute("colors", colorRepository.findAll());
		return this.mainPage;
	}

}
