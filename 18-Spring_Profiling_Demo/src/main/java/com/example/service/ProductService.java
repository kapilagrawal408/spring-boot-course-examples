package com.example.service;

import java.util.List;

import com.example.dto.ProductDTO;

public interface ProductService {
	public List<ProductDTO> getAllProducts();
	public ProductDTO saveProduct(ProductDTO productDTO);
}
