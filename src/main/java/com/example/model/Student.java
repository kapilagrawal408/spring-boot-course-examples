package com.example.model;

import com.example.validation.OnCreate;
import com.example.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
	@NotNull(groups = OnCreate.class, message = "{rollNo.NotNull}")
	@Positive(groups = OnCreate.class, message = "{rollNo.Positive}")
	private Integer rollNo;
	
	@NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "{name.NotBlank}")
	private String name;
	
	@NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "{marks.NotNull}")
	@PositiveOrZero(groups = {OnCreate.class, OnUpdate.class}, message = "{marks.PositiveOrZero}")
	private Double marks;
}