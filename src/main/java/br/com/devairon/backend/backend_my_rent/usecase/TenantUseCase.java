package br.com.devairon.backend.backend_my_rent.usecase;

import br.com.devairon.backend.backend_my_rent.domain.dto.TenantDTO;

import java.util.List;
import java.util.Optional;

public interface TenantUseCase {

    Optional<TenantDTO> createTenant(TenantDTO request);
    Optional<TenantDTO> getTenantByCPF(String cpf);

    Optional<List<TenantDTO>> getAllTenants();
    Optional<Boolean> updateTenant(Long id, TenantDTO request);
    Optional<Boolean> deleteTenant(Long id);
}
