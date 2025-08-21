package com.delta.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.delta.dtos.ProductRequestDTO;
import com.delta.dtos.ProductResponseDTO;
import com.delta.entities.Product;

@Mapper(componentModel="spring")
public interface ProductMapper {
	
	
	
	Product toEntity(ProductRequestDTO dto);
	List<ProductResponseDTO> toDTOList(List<Product> products);
	ProductResponseDTO toDTO(Product product);
	
	void updateEntityFromDto(ProductRequestDTO dto,@MappingTarget Product entity);
	
//	private ProductMapper() {}
//	 public static Product toEntity(ProductRequestDTO dto) {
//		 Product p = new Product();
//		 
//		 p.setName(dto.getName());
//		 p.setDescription(dto.getDescription());
//		 p.setPrice(dto.getPrice());
//		 p.setStock(dto.getStock());
//		 
//		 return p;
//	 }
//	 public static ProductResponseDTO toResponseDTO(Product p) {
//	        ProductResponseDTO dto = new ProductResponseDTO();
//	        
//	        dto.setId(p.getId());
//	        dto.setName(p.getName());
//	        dto.setDescription(p.getDescription());
//	        dto.setPrice(p.getPrice());
//	        dto.setStock(p.getStock());
//	        dto.setCreatedAt(p.getCreatedAt());
//	        dto.setUpdatedAt(p.getUpdatedAt());
//	        
//	        return dto;
//	        
//	 }
}
