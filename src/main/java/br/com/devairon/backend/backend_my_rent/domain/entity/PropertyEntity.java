package br.com.devairon.backend.backend_my_rent.domain.entity;

import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;
import br.com.devairon.backend.backend_my_rent.domain.enums.OccupationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "property")
@Table(name = "property")
@Data
@NoArgsConstructor
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int quantityRooms;
    private double rentValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity addressProperty;

    private OccupationStatus occupationStatus;

    public PropertyEntity(PropertyDTO propertyDTO) {
        this.description = propertyDTO.getDescription();
        this.quantityRooms = propertyDTO.getQuantityRooms();
        this.rentValue = propertyDTO.getRentValue();
        this.addressProperty = getAddressProperty();
        this.occupationStatus = propertyDTO.getOccupationStatus();
    }
}
