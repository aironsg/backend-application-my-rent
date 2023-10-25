package br.com.devairon.backend.backend_my_rent.domain.entity;

import br.com.devairon.backend.backend_my_rent.domain.enums.TypePlan;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypeUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "owner")
@Table(name = "owner")
@Data
@NoArgsConstructor
public class OwnerEntity extends UserEntity {
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;
    private TypePlan typePlan;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private int quantityProperties;
}
