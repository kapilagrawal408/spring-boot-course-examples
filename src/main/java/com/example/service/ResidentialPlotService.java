package com.example.service;

import com.example.dto.PageResponseDTO;
import com.example.dto.ResidentialPlotDTO;

public interface ResidentialPlotService {
	public PageResponseDTO<ResidentialPlotDTO> getMostExpensivePropertiesPageWiseSorted(int pageNumber, int pageSize);
}
