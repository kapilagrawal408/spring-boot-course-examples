package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Continent;
import com.example.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByIsoCode(String isoCode);

    List<Country> findByContinent(Continent continent);

    //Return type is List because a single currency is used in many countries like euro
    List<Country> findByCurrencyCode(String currencyCode);

    boolean existsByIsoCode(String isoCode);

    List<Country> findByPopulationGreaterThan(Long population);

    List<Country> findByContinentOrderByNameAsc(Continent continent);
}