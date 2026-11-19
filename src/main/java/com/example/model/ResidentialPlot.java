package com.example.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "residential_plots")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ResidentialPlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(value = AccessLevel.NONE)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String locality;

    @Column(name = "area_in_sq_ft", nullable = false)
    private Integer areaInSqFt;

    @Column(name = "price_in_lakhs", nullable = false)
    private Double priceInLakhs;

    // CRITICAL: Always store Enums as Strings to prevent database corruption if the Enum order changes
    @Enumerated(EnumType.STRING)
    @Column(name = "facing_direction", nullable = false)
    private FacingDirection facingDirection;

    @Column(name = "is_vastu_compliant", nullable = false)
    private Boolean isVastuCompliant;
    //interesting
    //Boolean isVastuCompliant the setter method is setIsVastuCompliant(Boolean)
    //boolean isVastuCompliant the setter method is setVastuCompliant(Boolean)

    @Column(name = "listed_date", nullable = false)
    private LocalDateTime listedDate;

    public ResidentialPlot(String locality, Integer areaInSqFt, Double priceInLakhs, FacingDirection facingDirection, 
    		Boolean isVastuCompliant, LocalDateTime listedDate) {
        this.locality = locality;
        this.areaInSqFt = areaInSqFt;
        this.priceInLakhs = priceInLakhs;
        this.facingDirection = facingDirection;
        this.isVastuCompliant = isVastuCompliant;
        this.listedDate = listedDate;
    }

    public enum FacingDirection {
        NORTH, EAST, SOUTH, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
    }
}