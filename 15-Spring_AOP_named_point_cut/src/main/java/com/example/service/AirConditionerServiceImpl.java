package com.example.service;

import lombok.RequiredArgsConstructor;

import com.example.dto.AirConditionerDTO;
import com.example.exception.ACAlreadyExistsWithDeviceIdException;
import com.example.exception.NoACFoundException;
import com.example.mapper.MapperEntityToFromDTO;
import com.example.model.AirConditioner;
import com.example.repository.AirConditionerRepository;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AirConditionerServiceImpl implements AirConditionerService {

    private final AirConditionerRepository repository;
    private final MapperEntityToFromDTO mapper;

    @Override
    public AirConditionerDTO add(AirConditionerDTO dto) {
        if(repository.existsByDeviceId(dto.getDeviceId()))
        		throw new ACAlreadyExistsWithDeviceIdException("Air Conditioner with Device ID " + dto.getDeviceId() + " already exists.");
        
        AirConditioner savedEntity = repository.save(mapper.toEntity(dto));
        return mapper.toDto(savedEntity);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<AirConditionerDTO> searchByAnyDetails(AirConditionerDTO searchCriteria) {
        AirConditioner probe = mapper.toEntity(searchCriteria);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<AirConditioner> example = Example.of(probe, matcher);
        List<AirConditioner> results = repository.findAll(example);

        if (results.isEmpty())
            throw new NoACFoundException("No Air Conditioners found matching the given criteria.");

        return mapper.toDtoList(results);
    }

    @Override
    public AirConditionerDTO addOrUpdate(Integer id, AirConditionerDTO dto) {
        return repository.findById(id).map(
        		existingAC -> {
	            existingAC.setDeviceId(dto.getDeviceId());
	            existingAC.setCompanyName(dto.getCompanyName());
	            existingAC.setModelName(dto.getModelName());
	            existingAC.setPrice(dto.getPrice());
	            existingAC.setMfdDate(dto.getMfdDate());
	            return mapper.toDto(repository.save(existingAC));
        		}
    		).orElseGet(() -> {
            if (repository.existsByDeviceId(dto.getDeviceId()))
                throw new ACAlreadyExistsWithDeviceIdException("Cannot create new AC. Device ID " + dto.getDeviceId() + " is already taken.");
            
            dto.setId(id);
            return mapper.toDto(repository.save(mapper.toEntity(dto)));
        });
    }

    @Override
    public AirConditionerDTO editPrice(Integer id, Double newPrice) {
        AirConditioner existingAC = repository.findById(id).orElseThrow(
        		() -> new NoACFoundException("Cannot update price. Air Conditioner with ID " + id + " not found.")
        	);

        existingAC.setPrice(newPrice);
        return mapper.toDto(repository.save(existingAC));
    }

    @Override
    public void deleteAirConditionById(Integer id) {
        if (!repository.existsById(id))
            throw new NoACFoundException("Cannot delete. Air Conditioner with ID " + id + " not found.");
        
        repository.deleteById(id);
    }
}