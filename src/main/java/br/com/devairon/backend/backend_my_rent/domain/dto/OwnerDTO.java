package br.com.devairon.backend.backend_my_rent.domain.dto;

import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypePlan;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    Long id;
    @NotBlank
    @NotNull
    @Size(min = 5, max = 30)
    String name;
    @NotBlank
    @NotNull
    @Size(min = 8, max = 13)
    String phoneNumber;
    @Email
    @Column(nullable = true)
    String email;
    @CPF
    @NotNull
    @NotBlank
    @Size(max = 15)
    String cpf;
    AddressEntity address;
    TypeUser type;
    TypePlan typePlan;
}
