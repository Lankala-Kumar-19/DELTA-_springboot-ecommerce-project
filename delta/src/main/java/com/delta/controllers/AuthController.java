package com.delta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delta.dtos.AuthResponse;
import com.delta.dtos.LoginRequest;
import com.delta.dtos.UserRequestDTO;
import com.delta.services.AuthService;
import com.delta.services.TokenBlacklistService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenBlacklistService tserv;
	@Autowired
	private AuthService ser;
	
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserRequestDTO dto) {
		return ser.registerUser(dto);
	}
	
	@PostMapping("/login")
	public AuthResponse loginUser(@RequestBody LoginRequest dto) {
		return ser.loginUser(dto);
	}

	@PostMapping("/logout")
	public String logoutUser(@RequestBody String token) {
		token=token.replace("\"", "");
		tserv.addToken(token);
		return "Logged out"+token;
	}
	
}
