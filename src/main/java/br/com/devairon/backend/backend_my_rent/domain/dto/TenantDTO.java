package br.com.devairon.backend.backend_my_rent.domain.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record TenantDTO(
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

        @NotNull
        LocalDate rentDate,

        @NotNull
        @NotBlank
        int quantityDependents,

        @NotNull
        AddressPropertyEntity addressProperty

) {
}
