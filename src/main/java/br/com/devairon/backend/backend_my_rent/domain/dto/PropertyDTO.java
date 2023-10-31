package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.OccupationStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO
 {
        //Long id;

        @NotBlank
        private String description;

        @Positive
        private int quantityRooms;

        @Positive
        private double rentValue;

        @NotNull
        private AddressEntity addressProperty;

        private OccupationStatus occupationStatus;
}
