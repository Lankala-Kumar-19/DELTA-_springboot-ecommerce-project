package com.delta.services;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.delta.dtos.ProductRequestDTO;
import com.delta.dtos.ProductResponseDTO;
import com.delta.entities.Product;
import com.delta.exceptions.OutOfStockException;
import com.delta.exceptions.ProductNotFoundException;
import com.delta.mappers.ProductMapper;
import com.delta.repos.ProductRepository;

@Service
public class ProductService {

	
	private final Logger logger=LoggerFactory.getLogger(ProductService.class);
	private final ProductRepository pRepo;
	private final ProductMapper mapper;
    
    public ProductService(ProductRepository pRepo,ProductMapper mapper) {
        this.pRepo = pRepo;
        this.mapper=mapper;
    }
	
	
//	public ProductService(ProductRepository pRepo) {
//		this.pRepo=pRepo;
//	}
	
	public ProductResponseDTO createProduct(ProductRequestDTO product) {
//		Product p = new Product();
//		
//		p.setDescription(description);
//		p.setName(name);
//		p.setPrice(price);
//		p.setStock(stock);
		
		
//		Product saved= pRepo.save(p);
		
		Product p = mapper.toEntity(product);
		
		Product saved = pRepo.save(p);
		
		return mapper.toDTO(saved);
	}
	
//	public Page<Product> getAllProducts(int page,int size,String searchTerm){
//		Pageable pageable=PageRequest.of(page, size);
//		return pRepo.findAllProducts(pageable,searchTerm);
//	}
	
	public List<ProductResponseDTO> getAllProducts(){
		List<Product> products =  pRepo.findAll();
		return mapper.toDTOList(products);
	}
	
	public ProductResponseDTO getProductById(Long id) {
		Product p =pRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("product with id "+id+" not found"));
		return mapper.toDTO(p);
	}
	
	public ProductResponseDTO updateProduct(Long id,ProductRequestDTO dto) {
//		Product p=getProductById(id);
		Product p =pRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("product with id "+id+" not found"));
		
		mapper.updateEntityFromDto(dto, p);
		
		
		Product saved = pRepo.save(p);
		
		return mapper.toDTO(saved);
		
	}
	
	public void deleteProduct(Long id) {
		pRepo.deleteById(id);
		logger.info("product with id {} is deleted",id);
	}
	
	public String buyProduct(Long productId,Long userId) {
		
		Product p =pRepo.findById(productId).orElseThrow(()-> new ProductNotFoundException("product with id "+productId+" not found"));
		
		if(p.getStock() == null || p.getStock() <= 0) 
			throw new OutOfStockException("product with id "+productId+" is out of stock");
		p.setStock(p.getStock()-1);
		pRepo.save(p);
		return "user with id "+userId+" purchased product "+p.getName()+" with id "+productId+" and remaining stock is "+p.getStock();
	}
	
	
}
