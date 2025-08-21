package com.delta.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delta.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findByEmail(String email);
}
