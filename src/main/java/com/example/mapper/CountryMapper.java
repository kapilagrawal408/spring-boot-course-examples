package com.example.mapper;

import com.example.dto.CountryDTO;
import com.example.dto.CountryRequestDTO;
import com.example.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO toDTO(Country country);

    List<CountryDTO> toDTOList(List<Country> countries);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    Country toEntity(CountryRequestDTO requestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    void updateEntity(CountryRequestDTO requestDTO, @MappingTarget Country country);
}
