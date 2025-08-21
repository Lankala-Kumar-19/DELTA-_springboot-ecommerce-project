package com.delta.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delta.dtos.OrderItemDTO;
import com.delta.dtos.OrderRequestDTO;
import com.delta.dtos.OrderResponseDTO;
import com.delta.entities.Order;
import com.delta.entities.OrderItem;
import com.delta.entities.Product;
import com.delta.entities.Status;
import com.delta.entities.User;
import com.delta.exceptions.OrderNotFoundException;
import com.delta.exceptions.ProductNotFoundException;
import com.delta.exceptions.UserNotFoundException;
import com.delta.mappers.OrderMapper;
import com.delta.repos.OrderRepository;
import com.delta.repos.ProductRepository;
import com.delta.repos.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private OrderMapper mapper;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private ProductRepository prepo;
	
	public OrderResponseDTO addOrder(OrderRequestDTO dto) {
		User u =userrepo.findById(dto.getUserId()).orElseThrow(()-> new UserNotFoundException("user not found"));
		Order o = new Order();
		o.setUser(u);
		o.setStatus(Status.PENDING);
		
		List<OrderItem> orderItems = buildOrderItems(dto.getItems(), o);
		o.setItems(orderItems);
		
		BigDecimal totalAmount = orderItems.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
		totalAmount= totalAmount.setScale(2, RoundingMode.HALF_UP);
		
		o.setTotalAmount(totalAmount);
		
		
		Order saved=repo.save(o);
		return mapper.toDTO(saved);
	}
	
	public OrderResponseDTO getById(Long id) {
		Order o = repo.findById(id).orElseThrow(()->new OrderNotFoundException("order not found"));
		OrderResponseDTO dto = mapper.toDTO(o);
		dto.setItems(mapper.toItemDTOList(o.getItems()));
		return dto;
		
	}
	
	public List<OrderResponseDTO> getOrdersByUserId(Long id){
		List<Order> orders = repo.findOrdersByUserId(id);
		return mapper.toDTOList(orders);
	}
	
	public List<OrderResponseDTO> getAllOrders(){
		return mapper.toDTOList(repo.findAll());
	}
	public String deleteById(Long id) {
		Order o = repo.findById(id).orElseThrow(()->new OrderNotFoundException("order not found"));
		repo.delete(o);
		return "deleted order by "+o.getUser().getName();
	}
	
	public OrderResponseDTO updateStatus(Long id,Status status) {
		Order o = repo.findById(id).orElseThrow(()->new OrderNotFoundException("order not found"));
		if(o.getStatus()==Status.COMPLETED) throw new IllegalStateException("order already delivered");
		String s= o.getStatus().name();
		o.setStatus(status);
		Order updated =repo.save(o);
		return mapper.toDTO(updated);
		//return "status changed from "+s+" to "+o.getStatus().name();
	}
	
	private List<OrderItem> buildOrderItems(List<OrderItemDTO> dto,Order order){
		List<OrderItem> items = new ArrayList<>();
		for(OrderItemDTO x : dto) {
			Product p = prepo.findById(x.getProductId()).orElseThrow(()-> new ProductNotFoundException("product not found"));
			if (x.getQuantity() <= 0) {
			    throw new IllegalArgumentException("Quantity must be > 0 for product: " + p.getName());
			}

			
			OrderItem item = new OrderItem();
			
			
			item.setProduct(p);
			item.setQuantity(x.getQuantity());
			item.setPrice(p.getPrice().multiply(BigDecimal.valueOf(x.getQuantity())));
			item.setOrder(order);
			items.add(item);
			
		}
		return items;
	}
	
}
