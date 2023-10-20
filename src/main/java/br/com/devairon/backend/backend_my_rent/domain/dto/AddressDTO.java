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
public class AddressDTO{
        @NotNull
        @NotBlank
        String zipCode;
        @NotNull
        @NotBlank
        String state;
        @NotNull
        @NotBlank
        String city;
        @NotNull
        @NotBlank
        String neighborhood;
        @NotNull
        @NotBlank
        String street;
        @NotNull
        @NotBlank
        String numbe;


}
