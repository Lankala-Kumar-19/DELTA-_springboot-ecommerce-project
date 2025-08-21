package com.delta.dtos;

import com.delta.entities.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

	//private String accessToken;
	//private String tokenType;
	//private String expiresAt;
	//private Long userId;
	private String token;
	private String email;
	private Role role;
	private Long id;
	
}
