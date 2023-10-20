package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestAddressDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AddressServiceTest {
    @Autowired
    private AddressService service;
    @Autowired
    private Optional<AddressDTO> response;

    private AddressDTO request;

    @BeforeEach
    public void setUp() {
        request = TestAddressDataGenerator.generatorRandomAddress();
    }

    @Test
    public void shouldCreateAddressOwnerWithFieldsOK() {
        Optional<AddressDTO> response = service.createAddressOwner(request);
        assertNotNull(response);
        assertEquals(request, response.get());
    }

    @Test
    public void shouldCreateAddressOwnerWithFieldZipCodeBlank(){
        AddressDTO request = TestAddressDataGenerator.generatorRandomAddressWithFieldZipCodeBlank();
        Optional<AddressDTO> response = service.createAddressOwnerWithFieldZipCodeBlank(request);
        assertNotNull(response);
        assertTrue(response.get().getZipCode().isEmpty());
    }

    @Test
    public void shouldNotCreateAddressOwnerWithFieldsInBlank(){
        AddressDTO request = TestAddressDataGenerator.generatorRandomAddressWithFieldsBlank();
        Optional<AddressDTO> response = service.createAddressOwner(request);
        assertNotNull(response);
        assertTrue(response.get().getZipCode().isEmpty());
        assertTrue(response.get().getState().isEmpty());
        assertTrue(response.get().getCity().isEmpty());
        assertTrue(response.get().getNeighborhood().isEmpty());
        assertTrue(response.get().getStreet().isEmpty());
        assertTrue(response.get().getNumbe().isEmpty());
    }

    @Test
    public void shouldReturnNullOptionalForEmptyOwnerAddress(){
        //TODO: melhorar este metodo.
        AddressDTO request = new AddressDTO();
        Optional<AddressDTO> response = service.createAddressOwner(request);
        assertNotNull(response);

    }
    @Test
    public void shouldNotCreateAddressOwnerWithFieldsNULL(){
        //TODO: melhorar este metodo.
        AddressDTO request = new AddressDTO();
        Optional<AddressDTO> response = service.createAddressOwner(request);
        assertNotNull(response);
    }


}
