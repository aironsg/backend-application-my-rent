package br.com.devairon.backend.backend_my_rent.usecase;

import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.OwnerEntity;

import java.util.Optional;

public interface OwnerUseCase {
    Optional<OwnerDTO> createOwner(OwnerDTO request);
    Optional<OwnerDTO> getOwnerById(Long id);
    Optional<OwnerDTO> getOwnerByCPF(String cpf);
    Optional<OwnerDTO> updateOwner(Long id,OwnerDTO request);




}
