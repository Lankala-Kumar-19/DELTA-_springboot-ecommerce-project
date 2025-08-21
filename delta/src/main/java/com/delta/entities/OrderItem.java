package com.delta.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="order_items")
public class OrderItem {

	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name="p_id")
	private Product product;
	
	@Column(name="oi_quantity")
	private Integer quantity;
	
	@Column(name="oi_price")
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name="o_id")
	private Order order;
	
}
