package br.com.devairon.backend.backend_my_rent.domain.entity;

import br.com.devairon.backend.backend_my_rent.domain.enums.TypeUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tb_inquilino")
@Data
@NoArgsConstructor
public class TenantEntity extends  UserEntity{

    private int quantityDepedents;

    @OneToOne
    private AddressPropertyEntity address;

    private TypeUser typeUser;

    private Date rentDate;



}
