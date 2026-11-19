package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.PageResponseDTO;
import com.example.dto.ResidentialPlotDTO;
import com.example.service.ResidentialPlotService;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/residentialplot")
@Slf4j
@AllArgsConstructor
@Validated
public class ResidentialPlotController {
	private final ResidentialPlotService residentialPlotService;
	
	@GetMapping("/getplots/{pageNumber}/{pageSize}")
	public ResponseEntity<PageResponseDTO<ResidentialPlotDTO>> getPlots(@PathVariable("pageNumber") @Min(value = 1L, message = "{pageNumber.Positive}") int pageNumber, @PathVariable("pageSize") @Min(value = 1L, message = "{pageSize.Positive}") int pageSize){
		log.info("Inside com.example.controller.ResidentialPlotController.getPlots");
		PageResponseDTO<ResidentialPlotDTO> residentialPlots = residentialPlotService.getMostExpensivePropertiesPageWiseSorted(pageNumber, pageSize);
		log.info("Leaving com.example.controller.ResidentialPlotController.getPlots");
		return new ResponseEntity<PageResponseDTO<ResidentialPlotDTO>>(residentialPlots, HttpStatus.OK);
	}
	
	//GET http://localhost:8080/residentialplot/getplots/1/2
	/*
		{
		    "content": [
		        {
		            "areaInSqFt": 2200,
		            "facingDirection": "SOUTH",
		            "id": 5,
		            "isVastuCompliant": false,
		            "listedDate": "2026-09-12T20:05:11.995541",
		            "locality": "Sector 49",
		            "priceInLakhs": 145.0
		        },
		        {
		            "areaInSqFt": 2000,
		            "facingDirection": "NORTH",
		            "id": 9,
		            "isVastuCompliant": true,
		            "listedDate": "2026-09-12T20:05:11.995541",
		            "locality": "Sector 53",
		            "priceInLakhs": 132.25
		        }
		    ],
		    "firstPage": true,
		    "hasNextPage": true,
		    "hasPreviousPage": false,
		    "isFirstPage": true,
		    "isLastPage": false,
		    "lastPage": false,
		    "totalPages": 5,
		    "totalRecords": 10,
		    "totalRecordsThisPage": 2
		}
	 * */
	//GET http://localhost:8080/residentialplot/getplots/3/3
	/*
		{
		    "content": [
		        {
		            "areaInSqFt": 1200,
		            "facingDirection": "NORTH",
		            "id": 1,
		            "isVastuCompliant": true,
		            "listedDate": "2026-09-12T20:05:11.994543",
		            "locality": "Sector 45",
		            "priceInLakhs": 85.5
		        },
		        {
		            "areaInSqFt": 1100,
		            "facingDirection": "EAST",
		            "id": 6,
		            "isVastuCompliant": true,
		            "listedDate": "2026-09-12T20:05:11.995541",
		            "locality": "Sector 50",
		            "priceInLakhs": 78.0
		        },
		        {
		            "areaInSqFt": 900,
		            "facingDirection": "WEST",
		            "id": 3,
		            "isVastuCompliant": false,
		            "listedDate": "2026-09-12T20:05:11.995541",
		            "locality": "Sector 47",
		            "priceInLakhs": 60.25
		        }
		    ],
		    "firstPage": false,
		    "hasNextPage": true,
		    "hasPreviousPage": true,
		    "isFirstPage": false,
		    "isLastPage": false,
		    "lastPage": false,
		    "totalPages": 4,
		    "totalRecords": 10,
		    "totalRecordsThisPage": 3
		}
	 * */
	
	//GET http://localhost:8080/residentialplot/getplots/6/2
	/*
		{
		    "detail": "No residential plot(s) Found for page 6",
		    "instance": "/residentialplot/getplots/6/2",
		    "status": 400,
		    "title": "No residential plot found"
		}
	 */
	
	//GET http://localhost:8080/residentialplot/getplots/0/0
	/*
		{
		    "detail": "URL parameter validation failed.",
		    "instance": "/residentialplot/getplots/0/0",
		    "status": 400,
		    "title": "Invalid URL Parameter",
		    "errors": {
		        "pageNumber": "Page number must be a positive number",
		        "pageSize": "Page size must be a positive number"
		    }
		}
	 * */
}