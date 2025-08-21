package com.delta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.delta.dtos.AuthResponse;
import com.delta.dtos.LoginRequest;
import com.delta.dtos.UserRequestDTO;
import com.delta.dtos.UserResponseDTO;
import com.delta.entities.User;
import com.delta.exceptions.InvalidCredentials;
import com.delta.exceptions.UserNotFoundException;
import com.delta.mappers.UserMapper;
import com.delta.repos.UserRepository;

@Service
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private JwtService jser;
	
	@Autowired
	private UserRepository repo;


	public String registerUser(UserRequestDTO dto) {
		User u = mapper.toEntity(dto);
		u.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		repo.save(u);
		
		return "user registered successfully";
	}
	
	public AuthResponse loginUser(LoginRequest req) {
		User u = repo.findByEmail(req.getEmail()).orElseThrow(()->new UserNotFoundException("User not found"));
		
		if(!passwordEncoder.matches(req.getPassword(), u.getPassword())) {
			throw new InvalidCredentials("invalid credentials");
		}
		String token=jser.generateToken(req.getEmail());
		return AuthResponse.builder()
				.token(token)
				.email(u.getEmail())
				.role(u.getRole())
				.id(u.getId())
				.build();
		//return mapper.toDTO(u);
	}
	
	
}
