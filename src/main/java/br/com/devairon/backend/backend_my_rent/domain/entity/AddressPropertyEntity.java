package br.com.devairon.backend.backend_my_rent.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_endereco_imovel")
@Data
@NoArgsConstructor
public class AddressPropertyEntity extends  AddressEntity{

    @OneToMany(mappedBy = "addressProperty", cascade = CascadeType.ALL)
    private List<PropertyEntity> properties;

}
