package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.OccupationStatus;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestAddressDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestOwnerDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestPropertyDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PropertyServiceTest {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyService service;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OwnerService ownerService;
    private PropertyDTO request;
    private PropertyDTO requestUpdate;
    private AddressDTO addressDTO;
    private AddressDTO addressDTOUpdate;
    private OwnerDTO ownerDTO;
    private Optional<AddressEntity> addressById;
    private Optional<AddressEntity> addressByIdUpdate;


    @BeforeEach
    public void setUp() {
        /*
         * ESSE METODO É RESPONSAVEL PELO CARREGAMENTO DE DADOS ANTES DA CHAMADA DE QUALQUER METODO TEST.
         * OBSERVE ABAIXO O EFEITO CASCATA ENTRE OS OBJETOS
         * ORDEM DAS CHAMADAS
         * 1- GERANDO  DADOS ALEATÓRIOS PARA UM OBJETO ADDRESS
         * 2- GERANDO  DADOS ALEATÓRIOS PARA UM OBJETO OWNER
         * 3- SALVANDO UM OBJETO OWNER NO BANCO DE DADOS
         * 4- BUSCANDO NO BANCO DE DADOS UM OWNER
         * 5- VERIFICANDO SE O OBJETO FOI ENCONTRADO
         * 6- OWNER CRIANDO O ENDEREÇO DA PROPERTY
         * 7- BUSCANDO NO BANCO DE DADOS O ENDEREÇO CRIADO PELO OWNER E TRANSFORMANDO EM UMA ENTIDADE
         * 8- GERANDO UM OBJETO PROPERTY INSERINDO O ENDEREÇO DO BANCO DE DADOS
         * */
        addressDTO = TestAddressDataGenerator.generatorRandomAddress();
        addressDTOUpdate = TestAddressDataGenerator.generatorRandomAddress();
        ownerDTO = TestOwnerDataGenerator.generatorRandomOwner();
        ownerService.createOwner(ownerDTO);
        Optional<OwnerDTO> ownerResponse = ownerService.getOwnerById(1L);
        if (ownerResponse.isPresent()) {
            ownerService.createAddressProperty(addressDTO);
        }
        addressById = addressService.getAddressById(1L).map(AddressEntity::new);
        request = TestPropertyDataGenerator.generatorRandomProperty(addressById);




    }

    @Test
    public void shouldCreateProperty() {
        Optional<PropertyDTO> response = service.createProperty(request);
        assertNotNull(response);
        assertEquals(request, response.get());
    }


    @Test
    public void shouldGetAllProperties() {
        Optional<AddressEntity> addressById;
        for (int i = 1; i <= 4; i++) {
            Long id = Long.parseLong(String.valueOf(i));
            ownerService.createAddressProperty(addressDTO);
            addressById = addressService.getAddressById(id).map(AddressEntity::new);
            request = TestPropertyDataGenerator.generatorRandomProperty(addressById);
            service.createProperty(request);
        }
        List<PropertyDTO> allProperties = service.getAllProperties();
        assertTrue(allProperties.size() == 4);

    }

    @Test
    public void shouldReturnListEmpty() {
        List<PropertyDTO> allProperties = service.getAllProperties();
        assertTrue(allProperties.isEmpty());

    }

    @Test
    public void shouldGetPropertyById() {

        Optional<PropertyDTO> response = service.createProperty(request);
        assertNotNull(response);
        Optional<PropertyDTO> propertyById = service.getPropertyById(1L);
        assertTrue(propertyById.isPresent());
        assertEquals(request, propertyById.get());
    }
    @Test
    public void shouldNotGetPropertyByIdInvalid() {

        Optional<PropertyDTO> response = service.createProperty(request);
        assertNotNull(response);
        Optional<PropertyDTO> propertyById = service.getPropertyById(2L);
       assertTrue(propertyById.isEmpty());
    }

    @Test
    public void shouldUpdateProperty(){
        service.createProperty(request);
        Optional<PropertyDTO> property = service.getPropertyById(1L);
        service.updateProperty(1L, TestPropertyDataGenerator.generatorRandomPropertyUpdate());
        Optional<PropertyDTO> response = service.getPropertyById(1L);
        assertNotNull(response);
        assertTrue(response.isPresent());
        assertNotEquals(property, response);
    }

    @Test
    public void shouldNotUpdatePropertyWithIdInvalid(){
        service.createProperty(request);
        Optional<PropertyDTO> response = service.updateProperty(2L, TestPropertyDataGenerator.generatorRandomPropertyUpdate());
        assertNotNull(response);
        assertFalse(response.isPresent());
    }

    @Test
    public void shouldInactivateProperty(){
        service.createProperty(request);
        Optional<Boolean> response = service.inactiveProperty(1L);
        assertNotNull(response);
        assertTrue(response.isPresent());
        assertTrue(response.get().booleanValue());
    }

    @Test
    public void shouldNotInactivatePropertyWithIdInvalid(){
        service.createProperty(request);
        Optional<Boolean> response = service.inactiveProperty(2L);
        assertNotNull(response);
        assertTrue(response.isPresent());
        assertFalse(response.get().booleanValue());
    }

    @Test
    public void shouldDeleteProperty(){
        service.createProperty(request);
        Optional<Boolean> response = service.deleteProperty(1L);
        assertNotNull(response);
        assertTrue(response.get().booleanValue());

    }

    @Test
    public void shouldNotDeletePropertyWithIdInvalid(){
        service.createProperty(request);
        Optional<Boolean> response = service.deleteProperty(2L);
        assertNotNull(response);
        assertFalse(response.get().booleanValue());

    }




}
