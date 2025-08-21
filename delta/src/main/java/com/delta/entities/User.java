package com.delta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data

@Entity
@Table(name="users")
public class User extends Auditable {

	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="u_id")
	private Long id;
	
	@Column(name="u_name",nullable=false)
	private String name;
	
	@Column(name="u_email",unique=true,nullable=false)
	private String email;
	
	@Column(name="u_password",nullable=false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="u_role")
	private Role role;
	

	
	
}
