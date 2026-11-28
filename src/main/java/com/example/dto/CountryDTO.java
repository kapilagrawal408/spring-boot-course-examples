package com.example.dto;

import java.time.LocalDateTime;

import com.example.model.Continent;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

//This class is for Response
//So making it immutable for the sake of avoiding caching invalidation

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryDTO {

    private final Long id;
    private final String isoCode;
    private final String name;
    private final String capital;
    private final String currencyCode;
    private final Continent continent;
    private final Long population;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime lastUpdated;
}