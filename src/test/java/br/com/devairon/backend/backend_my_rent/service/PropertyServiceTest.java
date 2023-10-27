package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestAddressDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestOwnerDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestPropertyDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.NoSuchElementException;
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
    private PropertyDTO requestNull;
    private AddressDTO addressDTO;
    private OwnerDTO ownerDTO;


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
        ownerDTO = TestOwnerDataGenerator.generatorRandomOwner();
        ownerService.createOwner(ownerDTO);
        Optional<OwnerDTO> ownerResponse = ownerService.getOwnerById(1L);
        if(ownerResponse.isPresent()){
            ownerService.createAddressProperty(addressDTO);
        }
        Optional<AddressEntity> addressById;
        Optional<AddressEntity> addressByIdNull;
        addressById = addressService.getAddressById(1L).map(AddressEntity::new);
        addressByIdNull = addressService.getAddressById(2L).map(AddressEntity::new);
        request = TestPropertyDataGenerator.generatorRandomProperty(addressById);
        requestNull = TestPropertyDataGenerator.generatorRandomProperty(addressById);


    }

    @Test
    public void shouldCreateProperty(){
        Optional<PropertyDTO> response = service.createProperty(request);
        assertNotNull(response);
        assertEquals(request, response.get());
    }







}
