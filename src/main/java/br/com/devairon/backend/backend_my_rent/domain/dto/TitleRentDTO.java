package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.domain.entity.OwnerEntity;
import br.com.devairon.backend.backend_my_rent.domain.entity.PropertyEntity;
import br.com.devairon.backend.backend_my_rent.domain.entity.TenantEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleRentDTO
 {
        Long id;

        @NotNull
        TenantEntity tenant;

        @NotNull
        OwnerEntity owner;

        @NotNull
        AddressEntity addressProperty;

        @NotNull
        PropertyEntity property;

        @Positive
        double valueRent;

        @Positive
        int contractLengthInMonths;

        boolean active;

        @NotNull
        LocalDate contractStartDate;

        @NotNull
        LocalDate contractEndDate;
}
