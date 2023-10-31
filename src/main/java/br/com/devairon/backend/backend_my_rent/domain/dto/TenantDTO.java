package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.PropertyEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypeUser;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantDTO

 {
        Long id;

        @NotBlank
        @Size(min = 5, max = 30)
        String name;

        @NotBlank
        @Size(min = 8, max = 13)
        String phoneNumber;

        @Email
        @Column(nullable = true)
        String email;

        @CPF
        @NotBlank
        String cpf;

        @NotNull
        LocalDate rentDate;

        @NotNull
        int quantityDependents;

        @NotNull
        PropertyEntity property;

        @NotNull TypeUser typeUser;
}
