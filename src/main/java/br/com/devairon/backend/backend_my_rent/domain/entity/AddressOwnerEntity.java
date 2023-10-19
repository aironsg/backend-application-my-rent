package br.com.devairon.backend.backend_my_rent.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "tb_endereco_proprietario")
@Data
@NoArgsConstructor
public class AddressOwnerEntity extends AddressEntity {

    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private OwnerEntity owner;

}
