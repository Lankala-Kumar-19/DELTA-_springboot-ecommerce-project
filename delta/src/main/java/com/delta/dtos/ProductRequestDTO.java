package com.delta.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data

public class ProductRequestDTO {

	private String name;
	private String description;
	private BigDecimal price;
	private int stock;
	
}
