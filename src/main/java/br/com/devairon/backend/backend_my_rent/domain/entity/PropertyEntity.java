package br.com.devairon.backend.backend_my_rent.domain.entity;

import br.com.devairon.backend.backend_my_rent.domain.enums.OccupationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_imovel")
@Data
@NoArgsConstructor
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String description;
    private int quantityRooms;
    private double rentValue;

    @OneToOne
    private AddressEntity addressProperty;
    private OccupationStatus occupationStatus;

}
