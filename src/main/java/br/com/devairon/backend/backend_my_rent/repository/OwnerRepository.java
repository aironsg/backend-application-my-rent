package br.com.devairon.backend.backend_my_rent.repository;

import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, String> {
    Optional<OwnerEntity> findByCpf(String cpf);
}
