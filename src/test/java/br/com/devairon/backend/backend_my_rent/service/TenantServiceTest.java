package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.TenantDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.domain.entity.PropertyEntity;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestAddressDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestOwnerDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestPropertyDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestTenantGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TenantServiceTest {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private TenantService service;
    private PropertyDTO propertyResponse;
    private TenantDTO request;
    private AddressDTO addressDTO;

    private OwnerDTO ownerDTO;
    private Optional<AddressEntity> addressById;
    private Optional<AddressEntity> addressByIdUpdate;
    private Optional<PropertyEntity> propertyById;

    @BeforeEach
    public void setUp() {
        /*
         * ESSE METODO É RESPONSAVEL PELO CARREGAMENTO DE DADOS ANTES DA CHAMADA DE QUALQUER METODO TEST.
         * OBSERVE ABAIXO O EFEITO CASCATA ENTRE OS OBJETOS
         * ORDEM DAS CHAMADAS
         *
         * 1- GERANDO  DADOS ALEATÓRIOS PARA UM OBJETO ADDRESS
         * 2- GERANDO  DADOS ALEATÓRIOS PARA UM OBJETO OWNER
         * 3- SALVANDO UM OBJETO OWNER NO BANCO DE DADOS
         * 4- BUSCANDO NO BANCO DE DADOS UM OWNER
         * 5- VERIFICANDO SE O OWNER FOI ENCONTRADO
         * 6- OWNER CRIANDO O ENDEREÇO DA PROPERTY
         * 7- BUSCANDO NO BANCO DE DADOS O ENDEREÇO CRIADO PELO OWNER E TRANSFORMANDO EM UMA ENTIDADE
         * 8- GERANDO  DADOS ALEATÓRIOS PARA UM OBJETO PROPERTY
         * 9- GERANDO  DADOS ALEATÓRIOS PARA UM OBJETO TENANT
         *
         * */
        addressDTO = TestAddressDataGenerator.generatorRandomAddress();
        ownerDTO = TestOwnerDataGenerator.generatorRandomOwner();
        ownerService.createOwner(ownerDTO);
        Optional<OwnerDTO> ownerResponse = ownerService.getOwnerById(1L);
        if (ownerResponse.isPresent()) {
            ownerService.createAddressProperty(addressDTO);
        }
        addressById = addressService.getAddressById(1L).map(AddressEntity::new);
        System.out.println(">>>>>>" + addressById.get());
        propertyResponse = TestPropertyDataGenerator.generatorRandomProperty(addressById);
        propertyService.createProperty(propertyResponse);
        propertyById = propertyService.getPropertyById(1L).map(PropertyEntity::new);
        System.out.println(">>>>>>" + propertyById.get());
        request = TestTenantGenerator.generateTenantRandom(propertyById);

    }


}
