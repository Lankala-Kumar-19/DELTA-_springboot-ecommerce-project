package com.delta.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDTO {

	@NotNull(message="Product ID is required")
	private Long productId;
	@Positive(message="Quantity must be greater than 0")
	private Integer quantity;
	
	
}
