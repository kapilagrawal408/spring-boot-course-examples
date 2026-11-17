package com.example.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.Pattern.Flag;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
	@NotNull(message = "{customer.id.notnull}")
	@Min(value = 1L, message = "{customer.id.min}")
	private Integer id;
	
	@NotBlank(message = "{customer.name.notblank}")
	@Size(min = 2, message = "{customer.name.size}")
	private String name;
	
	@PastOrPresent(message = "{customer.lastpurchasedate}")
	private LocalDate lastPurchaseDate;
	
	@NotBlank(message = "{customer.email.notblank}")
	@Pattern(regexp = "[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\\.[a-zA-Z0-9]{2,3}", flags = Flag.CASE_INSENSITIVE, message = "{customer.email.pattern}")
	private String email;
	
	@NotBlank(message = "{customer.mobile.notblank}")
	@Pattern(regexp = "[6-9][0-9]{9}", message = "{customer.mobile.pattern}")
	private String mobile;
}
