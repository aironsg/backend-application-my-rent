package br.com.devairon.backend.backend_my_rent.teste_data_generator;

import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.OccupationStatus;
import com.github.javafaker.Faker;

import java.util.Optional;

public class TestPropertyDataGenerator {

    public static final Faker faker = new Faker();
    private static PropertyDTO property = new PropertyDTO();



    public static PropertyDTO generatorRandomProperty(Optional<AddressEntity> address) {
        property.setAddressProperty(address.get());
        property.setDescription(faker.lorem().sentence());
        property.setRentValue(faker.number().numberBetween(400, 1500));
        property.setOccupationStatus(OccupationStatus.OCCUPIED);
        property.setQuantityRooms(faker.number().numberBetween(1,10));
        return property;
    }

    public static PropertyDTO generatorRandomPropertyUpdate() {
        property.setDescription(faker.lorem().sentence());
        property.setRentValue(faker.number().numberBetween(400, 1500));
        property.setOccupationStatus(OccupationStatus.OCCUPIED);
        property.setQuantityRooms(faker.number().numberBetween(1,10));
        return property;
    }

    public static PropertyDTO updateStatusPropertyForUNOCCUPIED(){

        property.setOccupationStatus(OccupationStatus.UNOCCUPIED);

        return property;
    }
}
