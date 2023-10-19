package br.com.devairon.backend.backend_my_rent.domain.entity;

import br.com.devairon.backend.backend_my_rent.domain.enums.TypePlan;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypeUser;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_proprietario")
@Data
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String cpf;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<AddressOwnerEntity> addresses;

    private TypeUser type;
    private TypePlan typePlan;




}
