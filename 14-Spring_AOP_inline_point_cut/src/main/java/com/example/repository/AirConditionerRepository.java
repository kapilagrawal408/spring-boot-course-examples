package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.AirConditioner;

public interface AirConditionerRepository extends JpaRepository<AirConditioner, Integer> {
  
    // Spring Data JPA dynamically implements this.
    // We will use this in the Service layer to trigger the ACAlreadyExistsWithDeviceIdException.
    boolean existsByDeviceId(String deviceId);
}