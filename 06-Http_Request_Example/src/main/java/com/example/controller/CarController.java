package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Car;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CarController {

    private List<Car> cars = new ArrayList<>();

    @PostConstruct
    public void init() {
    		log.info("Cars Initialized");
        cars.add(new Car("101", "Sedan", "red", 2020, Arrays.asList("sunroof", "navigation")));
        cars.add(new Car("102", "SUV", "blue", 2021, Arrays.asList("leather seats", "4WD")));
        cars.add(new Car("103", "Hatchback", "red", 2019, Arrays.asList("bluetooth")));
    }

    /**
     * Example: GET /cars/101;color=red;year=2020;options=sunroof,navigation
     * The path variable "carId" is 101, and the matrix variables
	 * after the semicolon are bound to the parameters of this method.
     */
    @GetMapping("/cars/{carId}")
    public String getCarWithMatrixVars(
            @PathVariable String carId,
            @MatrixVariable String color,
            @MatrixVariable int year,
            @MatrixVariable List<String> options) {
 
        Car car = cars.stream().filter(c -> c.getId().equals(carId)).findFirst().orElse(null);

        if (car == null) {
        		log.error("No cars found with id {}", carId);
            return "Car not found";
        }
        log.info("Getting details of car having carid {}", carId);
        return "Found car: " + car;
    }
}