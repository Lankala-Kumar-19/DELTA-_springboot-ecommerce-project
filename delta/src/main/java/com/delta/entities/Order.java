package com.delta.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Order extends Auditable{

	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="o_id")
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name="u_id")
	private User user;
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	private List<OrderItem> items=new ArrayList<>();
	
	@Column(name="o_total")
	private BigDecimal totalAmount;
	
	@Column(name="o_status")
	private Status status;
	
}
