package br.com.devairon.backend.backend_my_rent.teste_data_generator;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import com.github.javafaker.Faker;

public class TestAddressDataGenerator {

    public static final Faker faker = new Faker();

    public static AddressDTO generatorRandomAddress(){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setZipCode(faker.address().zipCode());
        addressDTO.setState(faker.address().state());
        addressDTO.setUF(faker.address().state());
        addressDTO.setCity(faker.address().cityName());
        addressDTO.setNeighborhood(faker.address().city());
        addressDTO.setStreet(faker.address().streetName());
        addressDTO.setNumber(faker.address().buildingNumber());
        return addressDTO;
    }

    public static AddressDTO generatorRandomAddressWithFieldZipCodeBlank(){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setZipCode("");
        addressDTO.setState(faker.address().state());
        addressDTO.setUF(faker.address().state());
        addressDTO.setCity(faker.address().cityName());
        addressDTO.setNeighborhood(faker.address().city());
        addressDTO.setStreet(faker.address().streetName());
        addressDTO.setNumber(faker.address().buildingNumber());
        return addressDTO;
    }

    public static AddressDTO generatorRandomAddressWithFieldsBlank(){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setZipCode("");
        addressDTO.setState("");
        addressDTO.setUF("");
        addressDTO.setCity("");
        addressDTO.setNeighborhood("");
        addressDTO.setStreet("");
        addressDTO.setNumber("");
        return addressDTO;
    }
}
