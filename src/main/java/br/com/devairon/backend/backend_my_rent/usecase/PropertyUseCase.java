package br.com.devairon.backend.backend_my_rent.usecase;

import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;

import java.util.List;
import java.util.Optional;

public interface PropertyUseCase {

    Optional<PropertyDTO> createProperty(PropertyDTO request);
    List<PropertyDTO> getAllProperties();
    Optional<PropertyDTO> getPropertyById(Long id);
    Optional<PropertyDTO> updateProperty(Long id,PropertyDTO request);
    Optional<PropertyDTO> deleteProperty(Long id);
    Optional<Boolean> inactiveProperty(Long id);

}
