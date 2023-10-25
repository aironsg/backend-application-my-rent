package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestAddressDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestOwnerDataGenerator;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OwnerServiceTest {

    //TODO: criar testes que façam a verificação do tempo do plano e tipo de plano
    //TODO: verificar a coverage dos testes e perceber que esta faltando cobertura no cadastro de dados do metodo create
    OwnerDTO request;
    OwnerDTO requestUpdate;
    AddressDTO address;
    @Autowired
    private OwnerService service;
    @Autowired
    private AddressService addressService;
    @Autowired
    ModelMapper mapper;
    @BeforeEach
    public void setUp() {
        address = TestAddressDataGenerator.generatorRandomAddress();
       addressService.createAddress(address);
        Optional<AddressDTO> addressResponse = addressService.getAddressById(1L);
        AddressEntity addressEntity = mapper.map(addressResponse, AddressEntity.class);
        request = TestOwnerDataGenerator.generatorRandomOwner(addressEntity);
        requestUpdate = TestOwnerDataGenerator.generatorRandomOwner(addressEntity);
    }

    @Test
    public void shouldCreateOwnerWithCompleteFields(){
        Optional<OwnerDTO> response = service.createOwner(request);
        assertNotNull(response);
        assertEquals(request, response.get());
    }
    @Test
    public void shouldNotCreateOwnerWithFieldsNULL(){
        OwnerDTO ownerDTO = new OwnerDTO();
        Optional<OwnerDTO> response = service.createOwner(ownerDTO);
        assertNotNull(response);
        assertFalse(response.isPresent());
    }

    @Test
    public void shouldGetOwnerWithCPF(){
        service.createOwner(request);
        Optional<OwnerDTO> response = service.getOwnerByCPF(request.getCpf());
        assertNotNull(response);
        assertNotNull(response.get().getCpf());
        assertEquals(request.getCpf(), response.get().getCpf());
    }

    @Test
    public void shouldNetGetOwnerWithCPFInvalid(){
        service.createOwner(request);
        Optional<OwnerDTO> response = service.getOwnerByCPF("000.111.222-34");
        assertNotNull(response);
        assertFalse(response.isPresent());
    }

    @Test
    public void shouldGetOwnerById(){
        service.createOwner(request);
        Optional<OwnerDTO> response = service.getOwnerById(1L);
        assertNotNull(response);
        assertEquals(1L, response.get().getId());
    }
    @Test
    public void shouldNotGetOwnerByIdInvalid(){
        service.createOwner(request);
        Optional<OwnerDTO> response = service.getOwnerById(2L);
        assertNotNull(response);
        assertFalse(response.isPresent());
    }

    @Test
    public void shouldUpdateOwnerByIdValid(){
        service.createOwner(request);
        Optional<OwnerDTO> response = service.updateOwner(1L, requestUpdate);
        assertNotNull(response);
        assertNotEquals(request, response.get());
    }

    @Test
    public void shouldNotUpdateOwnerByIdInvalid(){
        service.createOwner(request);
        Optional<OwnerDTO> response = service.updateOwner(2L, requestUpdate);
        assertNotNull(response);
        assertFalse(response.isPresent());
    }

   //TODO: não esquecer de implementar os demais testes.

}
