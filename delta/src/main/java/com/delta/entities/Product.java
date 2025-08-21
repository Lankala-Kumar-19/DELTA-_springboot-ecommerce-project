package com.delta.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(
		name="products",
		indexes= {
				@Index(name="idx_product_name",columnList="p_name")
		}
		)
public class Product extends Auditable{

	

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="p_id")
	private Long id;
	
	@Column(name="p_name",nullable=false)
	private String name;
	
	@Column(name="p_description")
	private String description;
	
	@Column(name="p_price",precision=10,scale=2,nullable=false)
	private BigDecimal price=BigDecimal.ZERO;
	
	@Column(name="p_stock",nullable=false,columnDefinition = "INT DEFAULT 0")
	private Integer stock=0;
	


	
	
	
}
