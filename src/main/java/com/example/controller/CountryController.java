package com.example.controller;

import com.example.dto.CountryDTO;
import com.example.dto.CountryRequestDTO;
import com.example.model.Continent;
import com.example.service.CountryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@Validated
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<CountryDTO> addCountry(@Valid @RequestBody CountryRequestDTO requestDTO) {
        CountryDTO created = countryService.addCountry(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    /*
     POST http://localhost:8080/countries
	{
	  "isoCode": "IN",
	  "name": "India",
	  "capital": "New Delhi",
	  "currencyCode": "INR",
	  "continent": "ASIA",
	  "population": 1400000000
	}
	
	Output
	{
	    "capital": "New Delhi",
	    "continent": "ASIA",
	    "createdAt": "2026-09-18 13:34:48",
	    "currencyCode": "INR",
	    "id": 1,
	    "isoCode": "IN",
	    "lastUpdated": "2026-09-18 13:34:48",
	    "name": "India",
	    "population": 1400000000
	}

	 Hibernate: insert into country (capital,continent,created_at,currency_code,iso_code,last_updated,name,population) values (?,?,?,?,?,?,?,?)
	 9 more countries on the same track	
    */
    
    @GetMapping("/{isoCode}")
    public ResponseEntity<CountryDTO> getByIsoCode(
            @PathVariable
            @Size(min = 2, max = 3, message = "{country.isoCode.size}")
            String isoCode) {
        return ResponseEntity.ok(countryService.getByIsoCode(isoCode));
    }
    
     /*
     GET http://localhost:8080/countries/JP
     {
	   "capital": "Tokyo",
	   "continent": "ASIA",
	   "createdAt": "2026-09-18 14:14:23",
	   "currencyCode": "JPY",
	   "id": 2,
	   "isoCode": "JP",
	   "lastUpdated": "2026-09-18 14:14:23",
	   "name": "Japan",
	   "population": 125000000
	 }
	 
	 CHECK CONSOLE NO SQL QUERY IS THERE BECAUSE RESULT IS TAKEN FROM CACHE
     */

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<CountryDTO>> getByContinent(
            @PathVariable Continent continent) {
        return ResponseEntity.ok(countryService.getByContinent(continent));
    }
    
     /*
     GET http://localhost:8080/countries/continent/ASIA
     
     [
	    {
	        "capital": "New Delhi",
	        "continent": "ASIA",
	        "createdAt": "2026-09-18 14:14:12",
	        "currencyCode": "INR",
	        "id": 1,
	        "isoCode": "IN",
	        "lastUpdated": "2026-09-18 14:14:12",
	        "name": "India",
	        "population": 1400000000
	    },
	    {
	        "capital": "Tokyo",
	        "continent": "ASIA",
	        "createdAt": "2026-09-18 14:14:23",
	        "currencyCode": "JPY",
	        "id": 2,
	        "isoCode": "JP",
	        "lastUpdated": "2026-09-18 14:14:23",
	        "name": "Japan",
	        "population": 125000000
	    },
	    {
	        "capital": "Bangkok",
	        "continent": "ASIA",
	        "createdAt": "2026-09-18 14:14:28",
	        "currencyCode": "THB",
	        "id": 3,
	        "isoCode": "TH",
	        "lastUpdated": "2026-09-18 14:14:28",
	        "name": "Thailand",
	        "population": 70000000
	    }
	 ]
	 
	 CHECK CONSOLE, QUERY IS THERE
	 
	 AGAIN HIT THE SAME URL
	 GET http://localhost:8080/countries/continent/ASIA
	 
	 CHECK CONSOLE, NO QUERY
	 
	 http://localhost:8080/countries/continent/ASIA_PACIFIC
	{
	    "detail": "Invalid value 'ASIA_PACIFIC' for parameter 'continent'",
	    "instance": "/countries/continent/ASIA_PACIFIC",
	    "status": 400,
	    "title": "Invalid Parameter Value"
	}
     */
    
    @GetMapping("/continent/{continent}/sorted")
    public ResponseEntity<List<CountryDTO>> getByContinentSorted(
            @PathVariable Continent continent) {
        return ResponseEntity.ok(countryService.getByContinentOrderByNameAsc(continent));
    }
    
    /*
    GET http://localhost:8080/countries/continent/ASIA/sorted
    [
	    {
	        "capital": "New Delhi",
	        "continent": "ASIA",
	        "createdAt": "2026-09-18 14:14:12",
	        "currencyCode": "INR",
	        "id": 1,
	        "isoCode": "IN",
	        "lastUpdated": "2026-09-18 14:14:12",
	        "name": "India",
	        "population": 1400000000
	    },
	    {
	        "capital": "Tokyo",
	        "continent": "ASIA",
	        "createdAt": "2026-09-18 14:14:23",
	        "currencyCode": "JPY",
	        "id": 2,
	        "isoCode": "JP",
	        "lastUpdated": "2026-09-18 14:14:23",
	        "name": "Japan",
	        "population": 125000000
	    },
	    {
	        "capital": "Bangkok",
	        "continent": "ASIA",
	        "createdAt": "2026-09-18 14:14:28",
	        "currencyCode": "THB",
	        "id": 3,
	        "isoCode": "TH",
	        "lastUpdated": "2026-09-18 14:14:28",
	        "name": "Thailand",
	        "population": 70000000
	    }
	]
    CHECK CONSOLE, QUERY IS THERE
    
    AGAIN HIT THE SAME URL
    
    GET http://localhost:8080/countries/continent/ASIA/sorted
    
    CHECK CONSOLE, NO QUERY
    */

    @GetMapping("/currency/{currencyCode}")
    public ResponseEntity<List<CountryDTO>> getByCurrencyCode(
            @PathVariable
            @Size(min = 2, max = 3, message = "{country.currencyCode.size}")
            String currencyCode) {
        return ResponseEntity.ok(countryService.getByCurrencyCode(currencyCode));
    }
    
    /*
     GET http://localhost:8080/countries/currency/EUR
     
     [
	    {
	        "capital": "Paris",
	        "continent": "EUROPE",
	        "createdAt": "2026-09-18 14:14:32",
	        "currencyCode": "EUR",
	        "id": 4,
	        "isoCode": "FR",
	        "lastUpdated": "2026-09-18 14:14:32",
	        "name": "France",
	        "population": 68000000
	    },
	    {
	        "capital": "Berlin",
	        "continent": "EUROPE",
	        "createdAt": "2026-09-18 14:14:36",
	        "currencyCode": "EUR",
	        "id": 5,
	        "isoCode": "DE",
	        "lastUpdated": "2026-09-18 14:14:36",
	        "name": "Germany",
	        "population": 84000000
	    }
	 ]
     
     CHECK CONSOLE, QUERY IS THERE
    
     AGAIN HIT THE SAME URL
    
     GET http://localhost:8080/countries/currency/EUR
     CHECK CONSOLE, NO QUERY
    */

    @GetMapping("/population/greater-than/{population}")
    public ResponseEntity<List<CountryDTO>> getByPopulationGreaterThan(
            @PathVariable
            @PositiveOrZero(message = "{country.population.negative}")
            Long population) {
        return ResponseEntity.ok(countryService.getByPopulationGreaterThan(population));
    }
    /*
     GET http://localhost:8080/countries/population/greater-than/215000000
     CHECK CONSOLE, QUERY IS THERE
    
     AGAIN HIT THE SAME URL
     GET http://localhost:8080/countries/population/greater-than/215000000
     CHECK CONSOLE, QUERY IS THERE because no caching
    */

    @PutMapping("/{isoCode}")
    public ResponseEntity<CountryDTO> updateCountry(
            @PathVariable
            @Size(min = 2, max = 3, message = "{country.isoCode.size}")
            String isoCode,
            @Valid @RequestBody CountryRequestDTO requestDTO) {
        CountryDTO updated = countryService.updateCountry(isoCode, requestDTO);
        return ResponseEntity.ok(updated);
    }
    /*
     PUT http://localhost:8080/countries/IN
     
	 {
	  "isoCode": "IN",
	  "name": "India",
	  "capital": "New Delhi",
	  "currencyCode": "INR",
	  "continent": "ASIA",
	  "population": 1500000000
	 }
	 
	 Output
	 
	 GET http://localhost:8080/countries/IN
	 
	 NO query because taken from cache
	 
	 GET http://localhost:8080/countries/continent/ASIA
	 
	 QUERY will be there because cache invalidated
	 
	 GET http://localhost:8080/countries/currency/EUR
     
     QUERY will be there because cache invalidated
     
     GET http://localhost:8080/countries/continent/ASIA/sorted
     
     QUERY will be there because cache invalidated
     */

    @DeleteMapping("/{isoCode}")
    public ResponseEntity<Void> deleteCountry(@PathVariable @Size(min = 2, max = 3, message = "{country.isoCode.size}") String isoCode) {
        countryService.deleteCountry(isoCode);
        return ResponseEntity.noContent().build();
    }
    
    /*
     DELETE http://localhost:8080/countries/IN
     
     GET http://localhost:8080/countries/IN
	 
	 Exception because no country for given country code
	 
	 GET http://localhost:8080/countries/continent/ASIA
	 
	 QUERY will be there because cache invalidated
	 
	 GET http://localhost:8080/countries/currency/EUR
     
     QUERY will be there because cache invalidated
     
     GET http://localhost:8080/countries/continent/ASIA/sorted
     
     QUERY will be there because cache invalidated
    */
    
    //HERE ARE THE ACTUATOR OUTPUT
    /*
	GET http://localhost:8080/actuator
	{
	  "_links": {
	  "self": {
	    "href": "http://localhost:8080/actuator",
	    "templated": false
	  },
	  "info": {
	    "href": "http://localhost:8080/actuator/info",
	    "templated": false
	  },
	  "mappings": {
	    "href": "http://localhost:8080/actuator/mappings",
	    "templated": false
	  },
	  "caches-cache": {
	    "href": "http://localhost:8080/actuator/caches/{cache}",
	    "templated": true
	  },
	  "caches": {
	    "href": "http://localhost:8080/actuator/caches",
	    "templated": false
	  },
	  "metrics": {
	    "href": "http://localhost:8080/actuator/metrics",
	    "templated": false
	  },
	  "metrics-requiredMetricName": {
	    "href": "http://localhost:8080/actuator/metrics/{requiredMetricName}",
	    "templated": true
	  }
	  }
	}
	
	GET http://localhost:8080/actuator/info
	{
	    "app": {
	        "name": "Spring Chaching Actuator Example",
	        "version": "1.0.0",
	        "developer": "Team Kapil"
	    }
	}
	
	GET http://localhost:8080/actuator/mappings
	{
	  "contexts": {
	    "Spring_boot_caching_example": {
	      "mappings": {
	        "servletFilters": [
	          {
	            "className": "org.springframework.boot.servlet.filter.OrderedRequestContextFilter",
	            "name": "requestContextFilter",
	            "servletNameMappings": [],
	            "urlPatternMappings": [
	              "/*"
	            ]
	          },
	          ...
	        ],
	        "dispatcherServlets": {...},
	        "servlets": [...]
	      }
	    }
	  }
	}
	
	GET http://localhost:8080/actuator/caches
	{
	    "cacheManagers": {
	        "cacheManager": {
	            "caches": {
	                "countriesByIso": {
	                    "target": "java.util.concurrent.ConcurrentHashMap"
	                },
	                "countriesByContinentSorted": {
	                    "target": "java.util.concurrent.ConcurrentHashMap"
	                },
	                "countriesByContinent": {
	                    "target": "java.util.concurrent.ConcurrentHashMap"
	                },
	                "countriesByCurrency": {
	                    "target": "java.util.concurrent.ConcurrentHashMap"
	                }
	            }
	        }
	    }
	}
	
	http://localhost:8080/actuator/caches/countriesByIso
	{
	    "cacheManager": "cacheManager",
	    "name": "countriesByIso",
	    "target": "java.util.concurrent.ConcurrentHashMap"
	}
	
	GET http://localhost:8080/actuator/metrics
	{
	    "names": [
	        "application.ready.time",
	        "application.started.time",
	        ...
	        "tomcat.sessions.rejected"
	    ]
	}
	
	GET http://localhost:8080/actuator/metrics/application.ready.time
	{
	    "availableTags": [
	        {
	            "tag": "main.application.class",
	            "values": [
	                "com.example.SpringBootCachingExampleApplication"
	            ]
	        }
	    ],
	    "baseUnit": "seconds",
	    "description": "Time taken for the application to be ready to service requests",
	    "measurements": [
	        {
	            "statistic": "VALUE",
	            "value": 8.384
	        }
	    ],
	    "name": "application.ready.time"
	}
    */
}