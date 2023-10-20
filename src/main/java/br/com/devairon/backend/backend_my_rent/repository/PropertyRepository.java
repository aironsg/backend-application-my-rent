package br.com.devairon.backend.backend_my_rent.repository;

import br.com.devairon.backend.backend_my_rent.domain.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, String> {
}
