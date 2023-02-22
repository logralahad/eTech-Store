package com.ulsa.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ulsa.main.entity.Role;
import com.ulsa.main.repository.RoleRepository;

@Controller
public class RoleController {

	private final RoleRepository roleRepository;
	private final String mainPage = "pages/private/Rol/Roles";
	private final String addPage = "pages/private/Rol/AddRole";
	private final String editPage = "pages/private/Rol/EditRole";

	@Autowired
	public RoleController(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	@GetMapping("/dashboard/roles")
	public String viewRolesPage(Model model) {
		System.out.println("Roles");
		model.addAttribute("roles", roleRepository.findAll());
		return this.mainPage;
	}

	@GetMapping("/dashboard/roles/agregar")
	public String viewAgregarRolePage(Model model) {
		System.out.println("Agregar role");
		model.addAttribute("newRole", new Role());
		return this.addPage;
	}
	
	@GetMapping("/role/edit/{id}")
	public String editRole(@PathVariable("id") long id, Model model) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		model.addAttribute("editRole", role);
		return this.editPage;
	}

	@GetMapping("/role/delete/{id}")
	public String deleteRole(@PathVariable("id") long id, Model model) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		roleRepository.delete(role);
		model.addAttribute("roles", roleRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/role/add")
	public String addRole(@Validated Role role, BindingResult result, Model model) {
		roleRepository.save(role);
		model.addAttribute("roles", roleRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/role/update/{id}")
	public String updateRole(@PathVariable("id") long id, @Validated Role role, BindingResult result, Model model) {
		roleRepository.save(role);
		model.addAttribute("roles", roleRepository.findAll());
		return this.mainPage;
	}

}
