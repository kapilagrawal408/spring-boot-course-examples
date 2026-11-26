package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AirConditionerDTO {
    private Integer id;
    private String deviceId;
    private String companyName;
    private String modelName;
    private Double price;
    private LocalDate mfdDate;

    public AirConditionerDTO(String deviceId, String companyName, String modelName, Double price, LocalDate mfdDate) {
        this.deviceId = deviceId;
        this.companyName = companyName;
        this.modelName = modelName;
        this.price = price;
        this.mfdDate = mfdDate;
    }
}