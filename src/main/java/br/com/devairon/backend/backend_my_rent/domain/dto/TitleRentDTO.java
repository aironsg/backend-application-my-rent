package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.domain.entity.OwnerEntity;
import br.com.devairon.backend.backend_my_rent.domain.entity.PropertyEntity;
import br.com.devairon.backend.backend_my_rent.domain.entity.TenantEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record TitleRentDTO(
        @NotNull
        TenantEntity tenant,
        @NotNull
        OwnerEntity owner,
        @NotNull
        AddressEntity addressProperty,
        @NotNull
        PropertyEntity property,
        @Positive
        double valueRent,
        @Positive
        int contractLengthInMonths,
        boolean active,

        @NotNull
        LocalDate contractStartDate,
        @NotNull
        LocalDate contractEndDate
) {
}
