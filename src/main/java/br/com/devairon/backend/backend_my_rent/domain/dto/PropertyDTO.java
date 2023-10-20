package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.OccupationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PropertyDTO(
        @NotBlank
        @NotNull
        String description,
        @Positive
        int quantityRooms,
        @Positive
        double rentValue,
        AddressEntity addressProperty,
        OccupationStatus occupationStatus
) {
}
