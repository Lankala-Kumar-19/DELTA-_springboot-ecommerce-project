package com.delta.dtos;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponseDTO {

	private Long productId;
	private String productName;
	private Integer quantity;
	private BigDecimal price;
}
