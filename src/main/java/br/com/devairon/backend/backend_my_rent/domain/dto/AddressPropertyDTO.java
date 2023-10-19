package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.PropertyEntity;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddressPropertyDTO(
        String zipCode,
        String state,
        String city,
        String neighborhood,
        String street,
        String number,
        @NotNull
        List<PropertyEntity> properties
) {
}
