package br.com.devairon.backend.backend_my_rent.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    Long id;
    String zipCode;

    @NotBlank
    String state;

    @NotBlank
    String UF;

    @NotBlank
    String city;

    @NotBlank
    String neighborhood;

    @NotBlank
    String street;

    @NotBlank
    String number;


}
