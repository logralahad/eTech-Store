package com.ulsa.main.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ulsa.main.entity.User;
import com.ulsa.main.repository.RoleRepository;
import com.ulsa.main.repository.UserRepository;
import com.ulsa.main.utils.PasswordEncoder;

@Controller
public class UserController {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder encoder;

	private final String mainPage = "pages/private/Usuario/Usuarios";
	private final String addPage = "pages/private/Usuario/AddUser";
	private final String editPage = "pages/private/Usuario/EditUser";

	@Autowired
	public UserController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
	}

	@GetMapping("/dashboard/usuarios")
	public String viewUsersPage(Model model) {
		System.out.println("Users");
		model.addAttribute("users", userRepository.findAll());
		return this.mainPage;
	}

	@GetMapping("/dashboard/usuarios/agregar")
	public String viewAgregarUserPage(Model model) {
		System.out.println("Agregar user");
		model.addAttribute("newUser", new User());
		model.addAttribute("roles", roleRepository.findAll());
		return this.addPage;
	}

	@GetMapping("/user/edit/{id}")
	public String editUser(@PathVariable("id") String id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		model.addAttribute("editUser", user);
		model.addAttribute("roles", roleRepository.findAll());
		return this.editPage;
	}

	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") String id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No existe ese elemento"));
		userRepository.delete(user);
		model.addAttribute("users", userRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/user/add")
	public String addUser(@Validated User user, BindingResult result, Model model) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setCreated_at(new Date());
		user.setIs_active(true);
		userRepository.save(user);
		model.addAttribute("users", userRepository.findAll());
		return this.mainPage;
	}

	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") String id, @Validated User user, @RequestParam String newPassword,
			BindingResult result, Model model) {
		if (!newPassword.isBlank() && !newPassword.isEmpty()) {
			user.setPassword(encoder.encode(newPassword));
		}

		userRepository.save(user);
		model.addAttribute("users", userRepository.findAll());
		return this.mainPage;
	}

}
