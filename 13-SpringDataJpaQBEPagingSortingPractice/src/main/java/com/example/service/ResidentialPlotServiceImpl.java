package com.example.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dto.PageResponseDTO;
import com.example.dto.ResidentialPlotDTO;
import com.example.exception.NoResidentialPlotException;
import com.example.mapper.Entity_DTO_Mapper;
import com.example.mapper.PageResponseMapper;
import com.example.model.ResidentialPlot;
import com.example.model.ResidentialPlot.FacingDirection;
import com.example.repository.ResidentialPlotRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResidentialPlotServiceImpl implements ResidentialPlotService {
	private final ResidentialPlotRepository residentialPlotRepository; 
	private final Entity_DTO_Mapper mapper;
	
	@PostConstruct
    public void seedResidentialPlots() {
        if (residentialPlotRepository.count() > 0) {
            return;
        }

        List<ResidentialPlot> plots = new ArrayList<>();
        plots.add(new ResidentialPlot("Sector 45", 1200, 85.5, FacingDirection.NORTH, true, LocalDateTime.now()));
        plots.add(new ResidentialPlot("Sector 46", 1500, 95.0, FacingDirection.EAST, true, LocalDateTime.now()));
        plots.add(new ResidentialPlot("Sector 47", 900,  60.25, FacingDirection.WEST, false, LocalDateTime.now()));
        plots.add(new ResidentialPlot("Sector 48", 1800, 120.75, FacingDirection.NORTH, true, LocalDateTime.now()));
        plots.add(new ResidentialPlot("Sector 49", 2200, 145.0, FacingDirection.SOUTH, false, LocalDateTime.now()));
        plots.add(new ResidentialPlot("Sector 50", 1100, 78.0,  FacingDirection.EAST, true, LocalDateTime.now()));
        plots.add(new ResidentialPlot("Sector 51", 1350, 99.5,  FacingDirection.NORTH_EAST, true, LocalDateTime.now()));
        plots.add(new ResidentialPlot("Sector 52", 1600, 110.0, FacingDirection.SOUTH_WEST, false, LocalDateTime.now()));
        plots.add(new ResidentialPlot("Sector 53", 2000, 132.25, FacingDirection.NORTH, true, LocalDateTime.now()));
        plots.add(new ResidentialPlot("Sector 54", 750,  52.0,  FacingDirection.WEST, false, LocalDateTime.now()));
        residentialPlotRepository.saveAll(plots);
    }
	
	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public PageResponseDTO<ResidentialPlotDTO> getMostExpensivePropertiesPageWiseSorted(int pageNumber, int pageSize){
		log.info("Inside com.example.service.ResidentialPlotServiceImpl.getMostExpensivePropertiesPageWiseSorted");
		
		Sort sort = Sort.by(
			Sort.Order.desc("priceInLakhs"),
			Sort.Order.asc("areaInSqFt")
		);
		
		//The pageNumber starts from 0 in Spring but in real life people understand from 1
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		
		Page<ResidentialPlot> page = residentialPlotRepository.findAll(pageable);
		
		if(page.getTotalElements() == 0)
			throw new NoResidentialPlotException("No residential plot(s) Found");			
		
		if(!page.hasContent())
			throw new NoResidentialPlotException("No residential plot(s) Found for page " + pageNumber);
		
		PageResponseDTO<ResidentialPlotDTO> pageResponse = PageResponseMapper.getPageResponse(page, mapper::toDtoListForResidentialPlot);
		log.info("Leaving com.example.service.ResidentialPlotServiceImpl.getMostExpensivePropertiesPageWiseSorted");
		return pageResponse;
	}
}
