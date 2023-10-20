package br.com.devairon.backend.backend_my_rent.repository;

import br.com.devairon.backend.backend_my_rent.domain.entity.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<TenantEntity, String> {
}
