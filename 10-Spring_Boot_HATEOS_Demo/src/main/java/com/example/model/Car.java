package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Car {
	private Long id;
    private String modelName;
    private String type;
    private Long companyId; // The Foreign Key
}
