package com.delta.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.delta.entities.Status;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDTO {

	private Long orderId;
	private Long userId;
	private List<OrderItemResponseDTO> items;
	private BigDecimal totalAmount;
	private Status status;
	private LocalDateTime createdAt;
}
