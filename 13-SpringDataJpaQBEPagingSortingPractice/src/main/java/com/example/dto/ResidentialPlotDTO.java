package com.example.dto;

import com.example.model.ResidentialPlot.FacingDirection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResidentialPlotDTO {
    private Integer id;
    private String locality;
    private Integer areaInSqFt;
    private Double priceInLakhs;
    private FacingDirection facingDirection;
    private Boolean isVastuCompliant;
    private LocalDateTime listedDate;

    // Custom constructor excluding the ID (Useful for Projections and Probe creation)
    public ResidentialPlotDTO(String locality, Integer areaInSqFt, Double priceInLakhs, FacingDirection facingDirection,
    		Boolean isVastuCompliant, LocalDateTime listedDate) {
        this.locality = locality;
        this.areaInSqFt = areaInSqFt;
        this.priceInLakhs = priceInLakhs;
        this.facingDirection = facingDirection;
        this.isVastuCompliant = isVastuCompliant;
        this.listedDate = listedDate;
    }
}