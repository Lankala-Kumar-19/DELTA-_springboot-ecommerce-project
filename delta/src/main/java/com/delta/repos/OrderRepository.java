package com.delta.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delta.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findOrdersByUserId(Long id);
}
