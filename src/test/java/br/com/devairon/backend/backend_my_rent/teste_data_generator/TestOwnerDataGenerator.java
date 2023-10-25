package br.com.devairon.backend.backend_my_rent.teste_data_generator;

import br.com.devairon.backend.backend_my_rent.controller.AddressController;
import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypePlan;
import br.com.devairon.backend.backend_my_rent.service.AddressService;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.time.LocalDate;


public class TestOwnerDataGenerator {
    public static final Faker faker = new Faker();



    public static LocalDate datenow = LocalDate.now();
    public static LocalDate dateplus30days = datenow.plusDays(30);
    public static LocalDate dateplus90days = datenow.plusDays(90);
    public static LocalDate dateplus180days = datenow.plusDays(180);
    public static LocalDate dateplus365days = datenow.plusDays(365);



    public static OwnerDTO generatorRandomOwner(AddressEntity addressEntity) {
        OwnerDTO ownerDTO = new OwnerDTO();
        AddressEntity address = addressEntity;
        ownerDTO.setAddress(address);
        ownerDTO.setName(faker.name().fullName());
        ownerDTO.setEmail("teste@teste.com");
        ownerDTO.setCpf("44866522070");
        ownerDTO.setPhoneNumber("81994911300");
        ownerDTO.setQuantityProperties(faker.number().numberBetween(1, 25));
        ownerDTO.setTypePlan(TypePlan.PLAN_TEST);
        ownerDTO.setPlanStartDate(datenow);
        ownerDTO.setPlanEndDate(dateplus30days);
        return ownerDTO;
    }

    public static OwnerDTO generatorRandomOwnerUpdate(AddressEntity addressEntity) {
        OwnerDTO ownerDTO = new OwnerDTO();
        AddressEntity address = addressEntity;
        ownerDTO.setAddress(address);
        ownerDTO.setName(faker.name().fullName());
        ownerDTO.setEmail("email_update@teste.com");
        ownerDTO.setCpf("44866522070");
        ownerDTO.setPhoneNumber("81994911300");
        ownerDTO.setQuantityProperties(faker.number().numberBetween(1, 25));
        ownerDTO.setTypePlan(TypePlan.PLAN_TEST);
        ownerDTO.setPlanStartDate(datenow);
        ownerDTO.setPlanEndDate(dateplus30days);
        return ownerDTO;
    }

    public static OwnerDTO generatorRandomOwnerUpdateWithEmailNULL(AddressEntity addressEntity) {
        OwnerDTO ownerDTO = new OwnerDTO();
        AddressEntity address = addressEntity;
        ownerDTO.setAddress(address);
        ownerDTO.setName(faker.name().fullName());
        ownerDTO.setCpf("44866522070");
        ownerDTO.setPhoneNumber("81994911300");
        ownerDTO.setQuantityProperties(faker.number().numberBetween(1, 25));
        ownerDTO.setTypePlan(TypePlan.PLAN_TEST);
        ownerDTO.setPlanStartDate(datenow);
        ownerDTO.setPlanEndDate(dateplus30days);
        return ownerDTO;
    }

    public static OwnerDTO generatorRandomOwnerWithEmailNull(AddressEntity addressEntity) {
        OwnerDTO ownerDTO = new OwnerDTO();
        AddressEntity address = addressEntity;
        ownerDTO.setAddress(address);
        ownerDTO.setName(faker.name().fullName());
        ownerDTO.setCpf("44866522070");
        ownerDTO.setPhoneNumber(faker.phoneNumber().phoneNumber());
        ownerDTO.setQuantityProperties(faker.number().numberBetween(1, 25));
        ownerDTO.setTypePlan(TypePlan.PLAN_TEST);
        ownerDTO.setPlanStartDate(datenow);
        ownerDTO.setPlanEndDate(dateplus30days);
        return ownerDTO;
    }
    public static OwnerDTO generatorRandomOwnerWithCPFInvalid(AddressEntity addressEntity) {
        OwnerDTO ownerDTO = new OwnerDTO();
        AddressEntity address = addressEntity;
        ownerDTO.setAddress(address);
        ownerDTO.setName(faker.name().fullName());
        ownerDTO.setEmail("teste@teste.com");
        ownerDTO.setCpf("4486652207023");//cpf invalido
        ownerDTO.setPhoneNumber(faker.phoneNumber().phoneNumber());
        ownerDTO.setQuantityProperties(faker.number().numberBetween(1, 25));
        ownerDTO.setTypePlan(TypePlan.PLAN_TEST);
        ownerDTO.setPlanStartDate(datenow);
        ownerDTO.setPlanEndDate(dateplus30days);
        return ownerDTO;
    }

    public static OwnerDTO generatorRandomOwnerWithEmailBlank() {
        OwnerDTO ownerDTO = new OwnerDTO();
        AddressEntity address = TestAddressDataGenerator.generatorRandomAddressEntity();
        ownerDTO.setAddress(address);
        ownerDTO.setName(faker.name().fullName());
        ownerDTO.setCpf("00011122233");
        ownerDTO.setPhoneNumber(faker.phoneNumber().phoneNumber());
        ownerDTO.setQuantityProperties(faker.number().numberBetween(1, 25));
        ownerDTO.setTypePlan(TypePlan.PLAN_TEST);
        ownerDTO.setPlanStartDate(datenow);
        ownerDTO.setPlanEndDate(dateplus30days);
        return ownerDTO;
    }
}
