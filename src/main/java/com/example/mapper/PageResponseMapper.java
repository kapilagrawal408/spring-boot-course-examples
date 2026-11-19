package com.example.mapper;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import com.example.dto.PageResponseDTO;

public class PageResponseMapper {
	//This can work with all types i.e. not specific for ResidentialPlot to ResidentialPlotDTO
	static public<E, D> PageResponseDTO<D> getPageResponse(Page<E> page, Function<List<E>, List<D>> contentMapper) {
	    PageResponseDTO<D> prDTO = new PageResponseDTO<>();
	    prDTO.setContent(contentMapper.apply(page.getContent()));
	    prDTO.setTotalPages(page.getTotalPages());
	    prDTO.setHasNextPage(page.hasNext());
	    prDTO.setHasPreviousPage(page.hasPrevious());
	    prDTO.setFirstPage(page.isFirst());
	    prDTO.setLastPage(page.isLast());
	    prDTO.setTotalRecordsThisPage(page.getNumberOfElements());
	    prDTO.setTotalRecords(page.getTotalElements());
	    return prDTO;
	}
}
