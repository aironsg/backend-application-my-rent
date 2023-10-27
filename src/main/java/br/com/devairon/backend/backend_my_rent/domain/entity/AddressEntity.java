package br.com.devairon.backend.backend_my_rent.domain.entity;


import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity(name = "address")
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zipCode;
    private String state;
    private String UF;
    private String city;
    private String neighborhood;
    private String street;
    private String number;

    public AddressEntity(AddressDTO addressDTO) {
        this.zipCode = addressDTO.getZipCode();
        this.state = addressDTO.getState();
        this.UF = addressDTO.getUF();
        this.city = addressDTO.getCity();
        this.neighborhood = addressDTO.getNeighborhood();
        this.street = addressDTO.getStreet();
        this.number = addressDTO.getNumber();
    }
}
