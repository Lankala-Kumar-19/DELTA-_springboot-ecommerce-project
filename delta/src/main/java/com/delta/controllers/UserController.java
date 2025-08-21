package com.delta.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delta.dtos.UserRequestDTO;
import com.delta.dtos.UserResponseDTO;
import com.delta.entities.User;
import com.delta.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userv;
	public UserController(UserService userv) {
		this.userv=userv;
	}
	
	
	@PostMapping
	public UserResponseDTO addUser(@RequestBody UserRequestDTO user) {
		return userv.registerUser(user);
	}
	
	@GetMapping
	public List<UserResponseDTO> getAll(){
		return userv.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public UserResponseDTO getById(@PathVariable Long id) {
		return userv.getUserById(id);
	}
	
	@GetMapping("{email}")
	public UserResponseDTO getByEmail(@PathVariable String email) {
		return userv.getByEmail(email);
	}
	
}
