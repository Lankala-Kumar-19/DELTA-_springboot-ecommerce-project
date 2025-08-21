package com.delta.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.delta.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
//	@Query("select p from products p where  lower(p.p_name) like lower(concat('%',:search,'%')))")
//	Page<Product> findAllProducts(Pageable page,@Param("search") String searchTerm);
}
