package com.delta.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delta.dtos.ProductRequestDTO;
import com.delta.dtos.ProductResponseDTO;
import com.delta.entities.Product;
import com.delta.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService pserv;
	public ProductController(ProductService pserv) {
		this.pserv=pserv;
	}
	
	@PostMapping
	public ProductResponseDTO addProduct(@RequestBody ProductRequestDTO product) {
		System.out.println(product.getName());
		return pserv.createProduct(product);
	}
	@GetMapping
	public List<ProductResponseDTO> getProducts(){
		return pserv.getAllProducts();
	}
	
//	@GetMapping("/a")
//	public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size,@RequestParam(required=false) String search){
//		Page<Product> p=pserv.getAllProducts(page, size, search);
//		return ResponseEntity.ok(p); 
//	}
	@GetMapping("/{id}")
	public ProductResponseDTO getById(@PathVariable Long id) {
		return pserv.getProductById(id);
	}
	@PutMapping("/{id}")
	public ProductResponseDTO updateProduct(@PathVariable Long id,@RequestBody ProductRequestDTO product) {
		return pserv.updateProduct(id,product);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		pserv.deleteProduct(id);
	}
	@PostMapping("/{id}/buy")
	public ResponseEntity<String> buyProduct(@PathVariable("id") Long productId, @RequestParam Long userId){
		String confirm=pserv.buyProduct(productId, userId);
		return ResponseEntity.ok(confirm);
	}
}
