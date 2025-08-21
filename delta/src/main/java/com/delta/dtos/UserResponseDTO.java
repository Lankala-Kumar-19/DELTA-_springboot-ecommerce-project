package com.delta.dtos;

import java.time.LocalDateTime;

import com.delta.entities.Role;

import lombok.Data;

@Data

public class UserResponseDTO {

	
	private Long id;
	private String name;
	private String email;
	private Role role;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
}
