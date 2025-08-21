package com.delta.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequestDTO {

	@NotNull(message="user id is required")
	private Long userId;
	@NotEmpty(message="Order must have at least one item")
	private List<@Valid OrderItemDTO> items;
}
