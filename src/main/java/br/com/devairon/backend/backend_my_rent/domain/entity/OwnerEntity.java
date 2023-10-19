package br.com.devairon.backend.backend_my_rent.domain.entity;

import br.com.devairon.backend.backend_my_rent.domain.enums.TypePlan;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypeUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_proprietario")
@Data
@NoArgsConstructor
public class OwnerEntity extends UserEntity {
    private AddressEntity address;
    private TypeUser type;
    private TypePlan typePlan;
}
