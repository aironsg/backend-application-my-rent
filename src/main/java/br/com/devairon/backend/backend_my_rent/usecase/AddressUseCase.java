package br.com.devairon.backend.backend_my_rent.usecase;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;

import java.util.Optional;

public interface AddressUseCase {

    Optional<AddressDTO> createAddressOwner(AddressDTO request);
    Optional<AddressDTO> createAddressProperty(AddressDTO request);

    Optional<AddressDTO> createAddressOwnerWithFieldZipCodeBlank(AddressDTO request);
}
