package com.example.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "air_conditioners")

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AirConditioner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    private Integer id; // Wrapper class
    
    @Column(name = "device_id", unique = true, nullable = false, length = 50)
    private String deviceId; 

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Column(name = "model_name", nullable = false, length = 100)
    private String modelName;

    @Column(nullable = false)
    private Double price;

    @Column(name = "mfd_date", nullable = false)
    private LocalDate mfdDate; 

    public AirConditioner(String deviceId, String companyName, String modelName, Double price, LocalDate mfdDate) {
        this.deviceId = deviceId;
        this.companyName = companyName;
        this.modelName = modelName;
        this.price = price;
        this.mfdDate = mfdDate;
    }
}