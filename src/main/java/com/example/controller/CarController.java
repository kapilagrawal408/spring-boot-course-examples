package com.example.controller;

import com.example.exception.NoCarFoundException;
import com.example.model.Car;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class CarController {

    private final List<Car> cars = new ArrayList<>();
    private Long nextId = 104L;

    @PostConstruct
    public void initData() {
        cars.add(new Car(101L, "Camry", "Sedan", 1L));
        cars.add(new Car(102L, "RAV4", "SUV", 1L));
        cars.add(new Car(103L, "Mustang", "Coupe", 2L));
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<EntityModel<Car>> getCar(@PathVariable Long id) {
        Car car = cars.stream().filter(c -> c.getId().equals(id)).findFirst().orElseThrow(() -> new NoCarFoundException("Car not found for given id: " + id));

        EntityModel<Car> entityModel = EntityModel.of(car,
        		linkTo(methodOn(CarController.class).getCar(id)).withSelfRel(),
            linkTo(methodOn(CompanyController.class).getCompany(car.getCompanyId())).withRel("parent-company"));

        return ResponseEntity.ok(entityModel);
    }
    
    /*
    GET http://localhost:8080/cars/101
	
	Output
	{
	    "_links": {
	        "self": {
	            "href": "http://localhost:8080/cars/101"
	        },
	        "parent-company": {
	            "href": "http://localhost:8080/companies/1"
	        }
	    },
	    "id": 101,
	    "modelName": "Camry",
	    "type": "Sedan",
	    "companyId": 1
	}
     */

    @GetMapping("/companies/{companyId}/cars")
    public ResponseEntity<CollectionModel<EntityModel<Car>>> getCarsByCompany(@PathVariable Long companyId) {
        List<EntityModel<Car>> carModels = cars.stream().filter(
        		car -> car.getCompanyId().equals(companyId)).map(
    				car -> EntityModel.of(
					car,
                    linkTo(methodOn(CarController.class).getCar(car.getId())).withSelfRel()
                )
			).collect(Collectors.toList());

        CollectionModel<EntityModel<Car>> collectionModel = CollectionModel.of(
        		carModels,
        		linkTo(methodOn(CarController.class).getCarsByCompany(companyId)).withSelfRel(),
            linkTo(methodOn(CompanyController.class).getCompany(companyId)).withRel("parent-company")
        );

        return ResponseEntity.ok(collectionModel);
    }
    
    /*
    GET http://localhost:8080/companies/1/cars
	
	Output
	{
	    "_embedded": {
	        "carList": [
	            {
	                "_links": {
	                    "self": {
	                        "href": "http://localhost:8080/cars/101"
	                    }
	                },
	                "id": 101,
	                "modelName": "Camry",
	                "type": "Sedan",
	                "companyId": 1
	            },
	            {
	                "_links": {
	                    "self": {
	                        "href": "http://localhost:8080/cars/102"
	                    }
	                },
	                "id": 102,
	                "modelName": "RAV4",
	                "type": "SUV",
	                "companyId": 1
	            }
	        ]
	    },
	    "_links": {
	        "self": {
	            "href": "http://localhost:8080/companies/1/cars"
	        },
	        "parent-company": {
	            "href": "http://localhost:8080/companies/1"
	        }
	    }
	}
    */

    @PostMapping("/cars")
    public ResponseEntity<EntityModel<Car>> createCar(@RequestBody Car newCar) {
        newCar.setId(nextId++);
        cars.add(newCar);

        EntityModel<Car> entityModel = EntityModel.of(
        		newCar,
            linkTo(methodOn(CarController.class).getCar(newCar.getId())).withSelfRel(),
            linkTo(methodOn(CompanyController.class).getCompany(newCar.getCompanyId())).withRel("parent-company")
        );

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
    
    /*
     POST http://localhost:8080/cars
     {
	   "modelName": "Civic",
	   "type": "Sedan",
	   "companyId": 3 
	 }
	 
	 Output
	 {
	    "_links": {
	        "self": {
	            "href": "http://localhost:8080/cars/104"
	        },
	        "parent-company": {
	            "href": "http://localhost:8080/companies/3"
	        }
	    },
	    "id": 104,
	    "modelName": "Civic",
	    "type": "Sedan",
	    "companyId": 3
	}
	 */
}
