package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.AddressPropertyEntity;
import br.com.devairon.backend.backend_my_rent.domain.entity.OwnerEntity;
import br.com.devairon.backend.backend_my_rent.domain.entity.TenantEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TitleRentDTO(
        @NotNull
        TenantEntity tenant,
        @NotNull
        OwnerEntity owner,
        @NotNull
        AddressPropertyEntity addressProperty,
        @Positive
        double valueRent,
        @Positive
        int contractLengthInMonths,
        boolean active
) {
}
