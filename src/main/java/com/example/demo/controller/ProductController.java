package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.modal.Product;
import com.example.demo.repository.ProductRepository;
import javax.validation.Valid;
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	//get all records 
	@GetMapping("/products")
	public List<Product> getAllProducts() {
	    return productRepository.findAll();
	}
	//new Product
	// Create a new Note
	@PostMapping("/products")
	public Product createNote(@Valid @RequestBody Product products) {
	    return productRepository.save(products);
	}
	// Get a Single Product
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable(value = "id") Long productId) {
	    return productRepository.findById(productId)
	            .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
	}
	
	// Update a Product
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable(value = "id") Long productId,
	                                        @Valid @RequestBody Product productDetails) {

		Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

		product.setPname(productDetails.getPname());
		product.setPrice(productDetails.getPrice());
		product.setQty(productDetails.getQty());

	    Product updatedProduct= productRepository.save(product);
	    return updatedProduct;
	}
	
	//Delete Product
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
		Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

		productRepository.delete(product);

	    return ResponseEntity.ok().build();
	}
	//Get Max price value product
	
	@GetMapping("/productmax")
	public List<Product> Getmaxprice()
	{
		return productRepository.Getmax();
	}
	//Get Min price value product
	@GetMapping("/productmin")
	public List<Product> Getminprice()
	{
		return productRepository.Getmin();
	}
	

}
