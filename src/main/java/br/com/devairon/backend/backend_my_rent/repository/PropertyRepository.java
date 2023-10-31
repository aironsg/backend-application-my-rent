package br.com.devairon.backend.backend_my_rent.repository;

import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.PropertyEntity;
import br.com.devairon.backend.backend_my_rent.infra.config.sql.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, String> {
    /*
    * Exemplo de consulta personalizada
    *
    * @Query(value = SQL.GET_PROPERTY_BY_ADDRESS)
    List<PropertyDTO> getPropertyByAddress(@Param("addressId") Long addressId);
    *
    *
    * */

}
