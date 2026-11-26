package com.example.mapper; // Placed in DTO package as per your specification

import java.util.List;

import org.mapstruct.Mapper;
import com.example.dto.AirConditionerDTO;
import com.example.model.AirConditioner;

@Mapper(componentModel = "spring")
public interface MapperEntityToFromDTO {
    AirConditionerDTO toDto(AirConditioner entity);
    AirConditioner toEntity(AirConditionerDTO dto);
    List<AirConditionerDTO> toDtoList(List<AirConditioner> entities);
}