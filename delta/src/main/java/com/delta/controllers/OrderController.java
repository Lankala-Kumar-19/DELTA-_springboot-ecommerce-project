package com.delta.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delta.dtos.OrderRequestDTO;
import com.delta.dtos.OrderResponseDTO;
import com.delta.entities.Status;
import com.delta.services.OrderService;
import com.delta.services.UserService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService ser;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public OrderResponseDTO addOrder(@RequestBody OrderRequestDTO dto,Principal principal) {
		String email=principal.getName();
		Long jwtUserId=userService.getByEmail(email).getId();
		dto.setUserId(jwtUserId);
		
		return ser.addOrder(dto);
	}
	
	@GetMapping("/{id}")
	public OrderResponseDTO getById(@PathVariable Long id) {
		return ser.getById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN') or principal.id== #userId")
	@GetMapping("/user/{userId}")
	public List<OrderResponseDTO> getByUserId(@PathVariable Long userId){
		return ser.getOrdersByUserId(userId);
	}
	@GetMapping
	public List<OrderResponseDTO> getAll(){
		return ser.getAllOrders();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}/status")
	public OrderResponseDTO updateStatus(@PathVariable Long id,@RequestBody Status status) {
		return ser.updateStatus(id, status);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		ser.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
