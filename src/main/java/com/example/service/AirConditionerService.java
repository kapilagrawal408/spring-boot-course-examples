package com.example.service;

import com.example.dto.AirConditionerDTO;
import java.util.List;

public interface AirConditionerService {
    AirConditionerDTO add(AirConditionerDTO dto);
    List<AirConditionerDTO> searchByAnyDetails(AirConditionerDTO searchCriteria);
    AirConditionerDTO addOrUpdate(Integer id, AirConditionerDTO dto);
    AirConditionerDTO editPrice(Integer id, Double newPrice);
    void deleteAirConditionById(Integer id);
}