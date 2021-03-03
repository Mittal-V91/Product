package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modal.Product;

public interface ProductRepository extends   JpaRepository<Product, Long> {
	@Query(value = "FROM Product WHERE price = (SELECT min(price) FROM Product);",nativeQuery = true)
	public List<Product> Getmin();
	

	@Query(value = "FROM Product WHERE price = (SELECT Max(price) FROM Product);",nativeQuery = true)
	public  List<Product> Getmax();
}
