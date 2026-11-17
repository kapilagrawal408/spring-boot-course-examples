package com.example.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerController {
	private List<Customer> customerList;
	
	public CustomerController() {
		customerList = new ArrayList<>();
	}
	
	@PostConstruct
	public void setCustomerDetails() {
		log.info("Setting the customer info");
		customerList.add(new Customer(1, "ABC", LocalDate.now(), "a@a.in", "9865321475"));
	}

	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		customerList.add(customer);
		log.info("Customer with id {} added successfully", customer.getId());
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	}
	
	/*
	 * post request http://localhost:8080/customers with method body
	 * {
	 * 	"id": 0,
	 * "name": "A",
	 * "lastPurchaseDate": "2026-08-30",
	 * "email": "a@b.in",
	 * "mobile": "988653252"
	 * }
	 * 
	 * The output
	 * {
	 * 	"detail": "Input validation failed. Please check the provided fields.",
	 * 	"instance": "/customers",
	 * 	"status": 400,
	 * 	"title": "Invalid Input",
	 * 	"invalid_fields": {
	 * 		"lastPurchaseDate": "Last purchase date must not be of future.",
	 * 		"name": "Customer name must be of two characters at least",
	 * 		"mobile": "Invalid mobile number",
	 * 		"id": "Customer id must be a positive value"
	 * 	}
	 * }
	 */
}
