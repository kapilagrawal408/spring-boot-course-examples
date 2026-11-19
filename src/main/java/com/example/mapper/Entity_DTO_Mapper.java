package com.example.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.dto.JobPostingDTO;
import com.example.dto.ResidentialPlotDTO;
import com.example.model.JobPosting;
import com.example.model.ResidentialPlot;

@Mapper(componentModel = "spring")
public interface Entity_DTO_Mapper {
    JobPostingDTO toDto(JobPosting entity);
    JobPosting toEntity(JobPostingDTO dto);
    List<JobPostingDTO> toDtoList(List<JobPosting> entities);
    
    ResidentialPlotDTO toDto(ResidentialPlot entity);
    ResidentialPlot toEntity(ResidentialPlotDTO dto);
    List<ResidentialPlotDTO> toDtoListForResidentialPlot(List<ResidentialPlot> entities);
}