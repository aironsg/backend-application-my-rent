package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestAddressDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
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
    public void shouldCreateAddressWithFieldsOK() {
        Optional<AddressDTO> response = service.createAddress(request);
        assertNotNull(response);
        assertEquals(request, response.get());
    }

    @Test
    public void shouldCreateAddressWithFieldZipCodeBlank(){
        AddressDTO request = TestAddressDataGenerator.generatorRandomAddressWithFieldZipCodeBlank();
        Optional<AddressDTO> response = service.createAddressWithFieldZipCodeBlank(request);
        assertNotNull(response);
        assertTrue(response.get().getZipCode().isEmpty());
    }

    @Test
    public void shouldNotCreateAddressWithFieldsInBlank(){
        AddressDTO request = TestAddressDataGenerator.generatorRandomAddressWithFieldsBlank();
        Optional<AddressDTO> response = service.createAddress(request);
        assertNotNull(response);
        assertTrue(response.get().getZipCode().isEmpty());
        assertTrue(response.get().getState().isEmpty());
        assertTrue(response.get().getCity().isEmpty());
        assertTrue(response.get().getNeighborhood().isEmpty());
        assertTrue(response.get().getStreet().isEmpty());
        assertTrue(response.get().getNumbe().isEmpty());
    }

    @Test
    public void shouldReturnAnEmptyOptionalIfTheFieldsNULL(){
        AddressDTO request = new AddressDTO();
        Optional<AddressDTO> response = service.createAddress(request);
        assertNotNull(response);
        assertTrue(response.isEmpty());

    }
    @Test
    public void shouldReturnAnEmptyListIfNoAddressWasRegistered(){
        List<AddressDTO> response = service.getAllAddress();
        assertNotNull(response);
        assertTrue(response.isEmpty());

    }

    @Test
    public void shouldReturnListOfAllAddress(){
        List<AddressDTO> allAddress = service.getAllAddress();
        assertNotNull(allAddress);
        assertTrue(allAddress.isEmpty());
        for (int i = 0; i < 4; i++){
            AddressDTO address = TestAddressDataGenerator.generatorRandomAddress();
            service.createAddress(address);
        }
        allAddress = service.getAllAddress();
        assertTrue(allAddress.size() == 4);

    }

    @Test
    public void shouldReturnAddressById(){
        AddressDTO address = TestAddressDataGenerator.generatorRandomAddress();
        service.createAddress(address);
        assertNotNull(address);
        Optional<AddressDTO> reponse = service.getAddressById(1L);
        assertEquals(1L, reponse.get().getId());
    }

    @Test
    public void shouldNotReturnAddressByIdNonexistent() throws NoSuchElementException {
        AddressDTO address = TestAddressDataGenerator.generatorRandomAddress();
        service.createAddress(address);
        assertNotNull(address);
        Optional<AddressDTO> reponse = service.getAddressById(2L);
        assertNotNull(reponse);
        assertTrue(reponse.isEmpty());
    }

    @Test
    public void shouldUpdatedAddressWithNewData(){
        AddressDTO address = TestAddressDataGenerator.generatorRandomAddress();
        service.createAddress(address);
        assertNotNull(address);
        Optional<AddressDTO> newAddress = service.getAddressById(1L);
        assertTrue(newAddress.isPresent());
        newAddress.get().setState("pernambuco");
        newAddress.get().setUF("PE");
        newAddress.get().setCity("caruaru");
        newAddress.get().setNeighborhood("joao mota");
        newAddress.get().setStreet("lapaz");
        newAddress.get().setNumbe("338");
        Optional<AddressDTO> respone = service.updateAddressById(newAddress.get(), 1L);
        assertNotEquals(request, respone.get());

    }

    @Test
    public void shouldNotUpdatedAddressWithNewDataIfIdNonexistent(){
        AddressDTO address = TestAddressDataGenerator.generatorRandomAddress();
        service.createAddress(address);
        assertNotNull(address);
        Optional<AddressDTO> newAddress = service.getAddressById(1L);
        newAddress.get().setState("pernambuco");
        newAddress.get().setUF("PE");
        newAddress.get().setCity("caruaru");
        newAddress.get().setNeighborhood("joao mota");
        newAddress.get().setStreet("lapaz");
        newAddress.get().setNumbe("338");
        Optional<AddressDTO> response = service.updateAddressById(newAddress.get(), 2L);
        assertFalse(response.isPresent());


    }

    @Test
    public void shouldDeleteAddressById(){
        service.createAddress(request);
        List<AddressDTO> allAddress = service.getAllAddress();
        assertNotNull(allAddress);
        assertTrue(allAddress.size() > 0);
        boolean response = service.deleteAddressProperty(1L);
        assertTrue(response);
    }

    @Test
    public void shouldNotDeleteAddressWithWrongId(){
        service.createAddress(request);
        List<AddressDTO> allAddress = service.getAllAddress();
        assertNotNull(allAddress);
        assertTrue(allAddress.size() > 0);
        boolean response = service.deleteAddressProperty(2L);
        assertFalse(response);
    }
}
