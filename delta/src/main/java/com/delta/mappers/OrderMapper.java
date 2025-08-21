package com.delta.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.delta.dtos.OrderItemDTO;
import com.delta.dtos.OrderItemResponseDTO;
import com.delta.dtos.OrderRequestDTO;
import com.delta.dtos.OrderResponseDTO;
import com.delta.entities.Order;
import com.delta.entities.OrderItem;

@Mapper(componentModel="spring")
public interface OrderMapper {

	
	Order toEntity(OrderRequestDTO dto);
	
	@Mapping(source="id",target="orderId")
	@Mapping(source="user.id",target="userId")
	@Mapping(source="items",target="items")
	OrderResponseDTO toDTO(Order order);
	
	
	List<OrderResponseDTO> toDTOList(List<Order> orders);
	
	 @Mapping(target="price", source="price")
	 @Mapping(target="quantity", source="quantity")
	 @Mapping(source = "product.id", target = "productId")
	 @Mapping(source = "product.name", target = "productName")
	 OrderItemResponseDTO toItemDTO(OrderItem item);
	
	List<OrderItemResponseDTO> toItemDTOList(List<OrderItem> items);
	@Mapping(target="order", ignore=true)
    @Mapping(target="price", ignore=true)
	OrderItem toItemEntity(OrderItemDTO dto);
}
