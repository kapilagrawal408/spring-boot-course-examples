package com.example.controller;

import com.example.dto.AirConditionerDTO;
import com.example.service.AirConditionerService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/air-conditioners")
@RequiredArgsConstructor
public class AirConditionerController {

    private final AirConditionerService service;
    
    //Database and POST (add), POST (search) PUT (upsert) are same as for the Spring_AOP_inline_point_cut
    @PatchMapping("/{id}/price")
    public ResponseEntity<AirConditionerDTO> editPrice(@PathVariable Integer id, @RequestParam Double newPrice) {
        AirConditionerDTO updatedAc = service.editPrice(id, newPrice);
        return ResponseEntity.ok(updatedAc);
    }
    
    /*
	PATCH http://localhost:8080/api/v1/air-conditioners/2/price?newPrice=45000.50
	
	{
	  "companyName": "Samsung",
	  "deviceId": "SS-AC-2002",
	  "id": 2,
	  "mfdDate": "2023-06-20",
	  "modelName": "WindFree 1.5 Ton",
	  "price": 45000.5
	}
	
	2026-09-15 19:39:31.759 [http-nio-8080-exec-2] INFO  com.example.aspect.AspectLogger - Enter: com.example.controller.AirConditionerController.editPrice() with argument[s] = [2, 45000.5]
	2026-09-15 19:39:31.872 [http-nio-8080-exec-2] INFO  com.example.aspect.AspectLogger - Enter: com.example.service.AirConditionerServiceImpl.editPrice() with argument[s] = [2, 45000.5]
	2026-09-15 19:39:31.938 [http-nio-8080-exec-2] INFO  com.example.aspect.AspectLogger - Exit: com.example.service.AirConditionerServiceImpl.editPrice() with result = AirConditionerDTO(id=2, deviceId=SS-AC-2002, companyName=Samsung, modelName=WindFree 1.5 Ton, price=45000.5, mfdDate=2023-06-20)
	2026-09-15 19:39:32.002 [http-nio-8080-exec-2] INFO  com.example.aspect.AspectLogger - Exit: com.example.controller.AirConditionerController.editPrice() with result = <200 OK OK,AirConditionerDTO(id=2, deviceId=SS-AC-2002, companyName=Samsung, modelName=WindFree 1.5 Ton, price=45000.5, mfdDate=2023-06-20),[]>
	
	http://localhost:8080/api/v1/air-conditioners/999/price?newPrice=45000.50
	
	{
	    "detail": "Cannot update price. Air Conditioner with ID 999 not found.",
	    "instance": "/api/v1/air-conditioners/999/price",
	    "status": 404,
	    "title": "Air Conditioner Not Found"
	}
	
	2026-09-15 19:41:11.390 [http-nio-8080-exec-4] INFO  com.example.aspect.AspectLogger - Enter: com.example.controller.AirConditionerController.editPrice() with argument[s] = [999, 45000.5]
	2026-09-15 19:41:11.405 [http-nio-8080-exec-4] INFO  com.example.aspect.AspectLogger - Enter: com.example.service.AirConditionerServiceImpl.editPrice() with argument[s] = [999, 45000.5]
	
	2026-09-15 19:41:11.415 [http-nio-8080-exec-4] ERROR com.example.aspect.AspectLogger - Exception in com.example.controller.AirConditionerController.editPrice() with cause = 'NULL' and exception = 'Cannot update price. Air Conditioner with ID 999 not found.'
	com.example.exception.NoACFoundException: Cannot update price. Air Conditioner with ID 999 not found.
	...
	at java.base/java.lang.Thread.run(Thread.java:1474)
    */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirConditionById(@PathVariable Integer id) {
        service.deleteAirConditionById(id);
        return ResponseEntity.noContent().build();
    }
    
    /*
	DELETE http://localhost:8080/api/v1/air-conditioners/999
	
	{
	    "detail": "Cannot delete. Air Conditioner with ID 999 not found.",
	    "instance": "/api/v1/air-conditioners/999",
	    "status": 404,
	    "title": "Air Conditioner Not Found"
	}
	
	2026-09-15 19:47:32.464 [http-nio-8080-exec-6] INFO  com.example.aspect.AspectLogger - Enter: com.example.controller.AirConditionerController.deleteAirConditionById() with argument[s] = [999]
	2026-09-15 19:47:32.467 [http-nio-8080-exec-6] INFO  com.example.aspect.AspectLogger - Enter: com.example.service.AirConditionerServiceImpl.deleteAirConditionById() with argument[s] = [999]
	
	2026-09-15 19:47:33.100 [http-nio-8080-exec-6] ERROR com.example.aspect.AspectLogger - Exception in com.example.service.AirConditionerServiceImpl.deleteAirConditionById() with cause = 'NULL' and exception = 'Cannot delete. Air Conditioner with ID 999 not found.'
	com.example.exception.NoACFoundException: Cannot delete. Air Conditioner with ID 999 not found.
	at com.example.service.AirConditionerServiceImpl.deleteAirConditionById(AirConditionerServiceImpl.java:86)
	...
	at java.base/java.lang.Thread.run(Thread.java:1474)
	
	2026-09-15 19:47:33.105 [http-nio-8080-exec-6] ERROR com.example.aspect.AspectLogger - Exception in com.example.controller.AirConditionerController.deleteAirConditionById() with cause = 'NULL' and exception = 'Cannot delete. Air Conditioner with ID 999 not found.'
	com.example.exception.NoACFoundException: Cannot delete. Air Conditioner with ID 999 not found.
	at com.example.service.AirConditionerServiceImpl.deleteAirConditionById(AirConditionerServiceImpl.java:86)
	...
	at java.base/java.lang.Thread.run(Thread.java:1474)
    */
}