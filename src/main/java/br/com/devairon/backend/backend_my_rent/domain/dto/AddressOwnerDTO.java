package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.OwnerEntity;
import jakarta.validation.constraints.NotNull;

public record AddressOwnerDTO(
        String zipCode,
        String state,
        String city,
        String neighborhood,
        String street,
        String number,
        @NotNull
        OwnerEntity owner
) {
}
