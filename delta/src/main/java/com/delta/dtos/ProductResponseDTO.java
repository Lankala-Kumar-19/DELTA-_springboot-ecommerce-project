package com.delta.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data

public class ProductResponseDTO {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private int stock;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
}
