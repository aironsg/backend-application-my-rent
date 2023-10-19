package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.AddressOwnerEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypePlan;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypeUser;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record OwnerDTO(
        @NotBlank
        @NotNull
        @Size(min = 5, max = 30)
        String name,
        @NotBlank
        @NotNull
        @Size(min = 8, max = 13)
        String phoneNumber,
        @Email
        @Column(nullable = true)
        String email,
        @CPF
        @NotNull
        @NotBlank
        String cpf,


        List<AddressOwnerEntity> address,

        TypeUser type,
        TypePlan typePlan
) {
}
