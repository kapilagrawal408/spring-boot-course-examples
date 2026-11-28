package com.example.service;

import com.example.dto.CountryDTO;
import com.example.dto.CountryRequestDTO;
import com.example.model.Continent;

import java.util.List;

public interface CountryService {
    CountryDTO getByIsoCode(String isoCode);
    List<CountryDTO> getByContinent(Continent continent);
    List<CountryDTO> getByCurrencyCode(String currencyCode);
    List<CountryDTO> getByContinentOrderByNameAsc(Continent continent);
    List<CountryDTO> getByPopulationGreaterThan(Long population);
    CountryDTO addCountry(CountryRequestDTO requestDTO);
    CountryDTO updateCountry(String isoCode, CountryRequestDTO requestDTO);
    void deleteCountry(String isoCode);
}