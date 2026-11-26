package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ProductDTO;
import com.example.mapper.ProductMapper;
import com.example.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
	private final ProductRepository repository;
    private final ProductMapper mapper;	
	
    @Override
	@Transactional
	public ProductDTO saveProduct(ProductDTO productDTO) {
		var entity = mapper.toEntity(productDTO);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
	}
    
	@Override
	public List<ProductDTO> getAllProducts() {
		return repository.findAll().stream()
				.map(mapper::toDto)
                .collect(Collectors.toList());
	}
}
