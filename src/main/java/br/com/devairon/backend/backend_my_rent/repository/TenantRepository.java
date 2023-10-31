package br.com.devairon.backend.backend_my_rent.repository;

import br.com.devairon.backend.backend_my_rent.domain.dto.TenantDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<br.com.devairon.backend.backend_my_rent.domain.entity.TenantEntity, String> {
    Optional<TenantDTO> findByCpf(String cpf);
}
