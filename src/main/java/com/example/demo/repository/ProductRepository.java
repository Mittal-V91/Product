package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modal.Product;

public interface ProductRepository extends   JpaRepository<Product, Long> {
	@Query(value = "Select * FROM ProductMaster.product_details WHERE price = (SELECT min(price) FROM ProductMaster.product_details);",nativeQuery = true)
	public List<Product> Getmin();
	

	@Query(value = "Select * FROM ProductMaster.product_details WHERE price = (SELECT Max(price) FROM ProductMaster.product_details);",nativeQuery = true)
	public  List<Product> Getmax();
}
