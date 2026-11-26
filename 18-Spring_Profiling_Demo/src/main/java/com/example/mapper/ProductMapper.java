package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.dto.ProductDTO;
import com.example.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO dto);
    ProductDTO toDto(Product entity);
}