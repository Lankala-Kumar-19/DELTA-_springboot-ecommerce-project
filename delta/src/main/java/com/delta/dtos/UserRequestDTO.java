package com.delta.dtos;

import com.delta.entities.Role;

import lombok.Data;

@Data

public class UserRequestDTO {
	private String name;
	private String email;
	private String password;
	private Role role;
}
