package com.example.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.CountryDTO;
import com.example.dto.CountryRequestDTO;
import com.example.exception.CountryAlreadyExistsException;
import com.example.exception.NoCountryForGivenIsoCode;
import com.example.mapper.CountryMapper;
import com.example.model.Continent;
import com.example.model.Country;
import com.example.repository.CountryRepository;

@Service
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService {
	private final CountryRepository countryRepository;
	private final CountryMapper countryMapper;
	
	public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
		this.countryRepository = countryRepository;
		this.countryMapper = countryMapper;
	}
	
	
	//Natural key lookup — the primary caching demonstration
	//key = "#name" should be same as the parameter name
	//if user call this method with isoCode = IN then in the ConcurrenHashMap countriesByIso
	//A key "IN" will have the DTO object returned by this method
	@Override
	@Cacheable(cacheNames = "countriesByIso", key = "#isoCode")
	public CountryDTO getByIsoCode(String isoCode) {
		Country country = countryRepository.findByIsoCode(isoCode).orElseThrow(() -> new NoCountryForGivenIsoCode(isoCode));
		return countryMapper.toDTO(country);
	}
	
	
	//Small fixed argument space (7 continents)
	@Override
	@Cacheable(cacheNames = "countriesByContinent", key = "#continent")
	public List<CountryDTO> getByContinent(Continent continent){
		List<CountryDTO> countryList = countryMapper.toDTOList(countryRepository.findByContinent(continent));
		return List.copyOf(countryList);
	}
	
	//Same argument as getByContinent, but different result ordering — must use a separate cache to avoid collision
	@Override
	@Cacheable(cacheNames = "countriesByContinentSorted", key = "#continent")
	public List<CountryDTO> getByContinentOrderByNameAsc(Continent continent){
		List<CountryDTO> list = countryMapper.toDTOList(countryRepository.findByContinentOrderByNameAsc(continent));
		return List.copyOf(list);
	}
	
	//Shared currencies (EUR, USD) — high read, low write
	@Override
	@Cacheable(cacheNames = "countriesByCurrency", key = "#currencyCode")
    public List<CountryDTO> getByCurrencyCode(String currencyCode) {
        return countryMapper.toDTOList(countryRepository.findByCurrencyCode(currencyCode));
    }
    
    //Contrast case: Argument space is unbounded; hit rate would be near-zero
    public List<CountryDTO> getByPopulationGreaterThan(Long population) {
        return countryMapper.toDTOList(countryRepository.findByPopulationGreaterThan(population));
    }
    
    //Cache the new country by ISO code; evict affected continent and currency collections
    @Override
    @Transactional
    @Caching(
    		//The CachePut operation will add the newly added entity to cache so in the GET operation we can get it from cache
    		//This annotation is for UpSert operation. In this case we are performing insert only.
    		//The #result is for newly added object
    		put = @CachePut(cacheNames = "countriesByIso", key = "#result.isoCode"),
        evict = {
            @CacheEvict(cacheNames = "countriesByContinent", allEntries = true),
            @CacheEvict(cacheNames = "countriesByContinentSorted", allEntries = true),
            @CacheEvict(cacheNames = "countriesByCurrency", allEntries = true)
        }
    )
    public CountryDTO addCountry(CountryRequestDTO requestDTO) {
        if (countryRepository.existsByIsoCode(requestDTO.getIsoCode())) {
            throw new CountryAlreadyExistsException(requestDTO.getIsoCode());
        }
        Country entity = countryMapper.toEntity(requestDTO);
        Country saved = countryRepository.save(entity);
        return countryMapper.toDTO(saved);
    }
    
    @Override
    @Transactional
    @Caching(
        put = @CachePut(cacheNames = "countriesByIso", key = "#isoCode"),
        evict = {
            @CacheEvict(cacheNames = "countriesByContinent", allEntries = true),
            @CacheEvict(cacheNames = "countriesByContinentSorted", allEntries = true),
            @CacheEvict(cacheNames = "countriesByCurrency", allEntries = true)
        }
    )
    public CountryDTO updateCountry(String isoCode, CountryRequestDTO requestDTO) {
        Country existing = countryRepository.findByIsoCode(isoCode)
                .orElseThrow(() -> new NoCountryForGivenIsoCode(isoCode));
        countryMapper.updateEntity(requestDTO, existing);
        Country saved = countryRepository.save(existing);
        return countryMapper.toDTO(saved);
    }
    
    //Remove from all relevant caches
    @Override
    @Transactional
    @Caching(evict = {
        @CacheEvict(cacheNames = "countriesByIso", key = "#isoCode"),
        @CacheEvict(cacheNames = "countriesByContinent", allEntries = true),
        @CacheEvict(cacheNames = "countriesByContinentSorted", allEntries = true),
        @CacheEvict(cacheNames = "countriesByCurrency", allEntries = true)
    })
    public void deleteCountry(String isoCode) {
        Country existing = countryRepository.findByIsoCode(isoCode)
                .orElseThrow(() -> new NoCountryForGivenIsoCode(isoCode));
        countryRepository.delete(existing);
    }
}
