package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductDTO;
import com.example.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductDTO dto) {
        return service.saveProduct(dto);
    }
    
    /*
    POST http://localhost:8080/api/v1/products

    Request Body
    {
      "name": "chair",
      "category": "furniture",
      "price": 2563
    }
    
    Output
    {
      "id": "1"
      "name": "chair",
      "category": "furniture",
      "price": 2563
    }
    */
    
    @GetMapping
    public List<ProductDTO> getAll() {
        return service.getAllProducts();
    }
    
    /*
    GET http://localhost:8080/api/v1/products

    [
        {
            "category": "furniture",
            "id": 1,
            "name": "chair",
            "price": 2563.0
        }
    ]
    */
}