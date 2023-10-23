package br.com.devairon.backend.backend_my_rent.usecase;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressUseCase {

    Optional<AddressDTO> createAddress(AddressDTO request);

    Optional<AddressDTO> createAddressWithFieldZipCodeBlank(AddressDTO request);
    Optional<AddressDTO> updateAddressById(AddressDTO request,Long id);

    Optional<AddressDTO> getAddressById(Long id);
    List<AddressDTO> getAllAddress();

    boolean deleteAddressProperty(Long id);
}
