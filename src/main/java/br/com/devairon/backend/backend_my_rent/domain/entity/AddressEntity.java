package br.com.devairon.backend.backend_my_rent.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public  class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String zipCode;

    @NotNull
    @NotBlank
    private String state;
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String neighborhood;
    @NotNull
    @NotBlank
    private String street;
    @NotNull
    @NotBlank
    private String number;
}
