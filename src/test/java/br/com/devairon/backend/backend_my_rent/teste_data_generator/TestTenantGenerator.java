package br.com.devairon.backend.backend_my_rent.teste_data_generator;

import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.TenantDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.PropertyEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.TypeUser;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Optional;

public class TestTenantGenerator {
    public static final Faker faker = new Faker();
    public static TenantDTO tenantEntity = new TenantDTO();
    public static LocalDate datenow = LocalDate.now();
    public static LocalDate dateplus30days = datenow.plusDays(30);
    public static TenantDTO generateTenantRandom(Optional<PropertyEntity> property) {

        tenantEntity.setName(faker.name().fullName());
        tenantEntity.setCpf("12173617079");
        tenantEntity.setEmail("teste@email.com");
        tenantEntity.setPhoneNumber(faker.phoneNumber().phoneNumber());
        tenantEntity.setRentDate(dateplus30days);
        tenantEntity.setQuantityDependents(faker.number().numberBetween(0,6));
        tenantEntity.setTypeUser(TypeUser.TENANT);
        tenantEntity.setProperty(property.get());
        return tenantEntity;
    }
}
