package com.example.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exception.NoCompanyFoundException;
import com.example.model.Company;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/companies")
public class CompanyController {
	private final List<Company> companies = new ArrayList<>();
	private Long nextId = 3L;
	
	@PostConstruct
    public void initData() {
        companies.add(new Company(1L, "Toyota", "Japan"));
        companies.add(new Company(2L, "Ford", "USA"));
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<EntityModel<Company>> getCompany(@PathVariable Long id) {
        Company company = companies.stream().filter(c -> c.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NoCompanyFoundException("Company not found for given id " + id));

        EntityModel<Company> entityModel = EntityModel.of(company,
            linkTo(methodOn(CompanyController.class).getCompany(id)).withSelfRel(),
            linkTo(methodOn(CompanyController.class).getAllCompanies()).withRel("all-companies"),
            linkTo(methodOn(CarController.class).getCarsByCompany(id)).withRel("manufactured-cars")
        );

        return ResponseEntity.ok(entityModel);
    }
	
	/*
	 GET http://localhost:8080/companies/1
	 
	 OUTPUT
	 {
	    "_links": {
	        "self": {
	            "href": "http://localhost:8080/companies/1"
	        },
	        "all-companies": {
	            "href": "http://localhost:8080/companies"
	        },
	        "manufactured-cars": {
	            "href": "http://localhost:8080/companies/1/cars"
	        }
	    },
	    "id": 1,
	    "name": "Toyota",
	    "headquarters": "Japan"
	}
	*/
	
	@GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Company>>> getAllCompanies() {
        List<EntityModel<Company>> companyModels = companies.stream().map(company -> EntityModel.of(company,
            linkTo(methodOn(CompanyController.class).getCompany(company.getId())).withSelfRel(),
            linkTo(methodOn(CarController.class).getCarsByCompany(company.getId())).withRel("manufactured-cars"))
    		).collect(Collectors.toList());

        CollectionModel<EntityModel<Company>> collectionModel = CollectionModel.of(
    			companyModels,
            linkTo(methodOn(CompanyController.class).getAllCompanies()).withSelfRel()
        );

        return ResponseEntity.ok(collectionModel);
    }

	/*
	 GET http://localhost:8080/companies
	 
	 OUTPUT
	{
	    "_embedded": {
	        "companyList": [
	            {
	                "_links": {
	                    "self": {
	                        "href": "http://localhost:8080/companies/1"
	                    },
	                    "manufactured-cars": {
	                        "href": "http://localhost:8080/companies/1/cars"
	                    }
	                },
	                "id": 1,
	                "name": "Toyota",
	                "headquarters": "Japan"
	            },
	            {
	                "_links": {
	                    "self": {
	                        "href": "http://localhost:8080/companies/2"
	                    },
	                    "manufactured-cars": {
	                        "href": "http://localhost:8080/companies/2/cars"
	                    }
	                },
	                "id": 2,
	                "name": "Ford",
	                "headquarters": "USA"
	            }
	        ]
	    },
	    "_links": {
	        "self": {
	            "href": "http://localhost:8080/companies"
	        }
	    }
	}
	*/
	
	@PostMapping
    public ResponseEntity<EntityModel<Company>> createCompany(@RequestBody Company newCompany) {
        newCompany.setId(nextId++);
        companies.add(newCompany);

        EntityModel<Company> entityModel = EntityModel.of(newCompany,
                linkTo(methodOn(CompanyController.class).getCompany(newCompany.getId())).withSelfRel(),
                linkTo(methodOn(CompanyController.class).getAllCompanies()).withRel("all-companies"));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
	/*
	POST http://localhost:8080/companies
	{
	  "name": "Honda",
	  "headquarters": "Minato, Tokyo, Japan"
	}
	
	Output
	{
	    "_links": {
	        "self": {
	            "href": "http://localhost:8080/companies/3"
	        },
	        "all-companies": {
	            "href": "http://localhost:8080/companies"
	        }
	    },
	    "id": 3,
	    "name": "Honda",
	    "headquarters": "Minato, Tokyo, Japan"
	}
	*/
	
	@PutMapping("/{id}")
    public ResponseEntity<EntityModel<Company>> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        Company company = companies.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoCompanyFoundException("Company not found for given id: " + id));

        company.setName(updatedCompany.getName());
        company.setHeadquarters(updatedCompany.getHeadquarters());

        EntityModel<Company> entityModel = EntityModel.of(company,
            linkTo(methodOn(CompanyController.class).getCompany(id)).withSelfRel(),
            linkTo(methodOn(CompanyController.class).getAllCompanies()).withRel("all-companies")
    		);

        return ResponseEntity.ok(entityModel);
    }
	
	/*
	PUT http://localhost:8080/companies/1
	{
	  "name": "Toyota Motor Corp",
	  "headquarters": "Toyota City, Japan"
	}
	
	Output
	{
	    "_links": {
	        "self": {
	            "href": "http://localhost:8080/companies/1"
	        },
	        "all-companies": {
	            "href": "http://localhost:8080/companies"
	        }
	    },
	    "id": 1,
	    "name": "Toyota Motor Corp",
	    "headquarters": "Toyota City, Japan"
	}
	*/
}