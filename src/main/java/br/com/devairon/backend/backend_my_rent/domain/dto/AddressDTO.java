package br.com.devairon.backend.backend_my_rent.domain.dto;

public record AddressDTO(
        String zipCode,
        String state,
        String city,
        String neighborhood,
        String street,
        String number

) {
}
