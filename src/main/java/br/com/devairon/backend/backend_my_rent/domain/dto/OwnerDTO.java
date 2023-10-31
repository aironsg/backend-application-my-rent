package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypePlan;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypeUser;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    private Long id;
    @NotBlank
    @Size(min = 5, max = 30)
    private String name;
    @NotBlank
    @Size(min = 8, max = 18)
    private String phoneNumber;
    @Email
    @Column(nullable = true)
    private String email;
    @CPF
    @NotBlank
    @Size(max = 15)
    private String cpf;

    @Positive
    private int quantityProperties;

    @NotNull
    private TypePlan typePlan;
    @NotNull
    private LocalDate planStartDate;
    @NotNull
    private LocalDate planEndDate;
}
