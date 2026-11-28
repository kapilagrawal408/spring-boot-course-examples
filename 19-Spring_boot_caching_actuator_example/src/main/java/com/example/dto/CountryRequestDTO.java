package com.example.dto;

import com.example.model.Continent;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryRequestDTO {

    @NotBlank(message = "{country.isoCode.required}")
    @Size(min = 2, max = 3, message = "{country.isoCode.size}")
    private String isoCode;

    @NotBlank(message = "{country.name.required}")
    @Size(max = 60, message = "{country.name.size}")
    private String name;

    @NotBlank(message = "{country.capital.required}")
    @Size(max = 100, message = "{country.capital.size}")
    private String capital;

    @NotBlank(message = "{country.currencyCode.required}")
    @Size(min = 3, max = 3, message = "{country.currencyCode.size}")
    private String currencyCode;

    @NotNull(message = "{country.continent.required}")
    private Continent continent;

    @PositiveOrZero(message = "{country.population.negative}")
    private Long population;
}