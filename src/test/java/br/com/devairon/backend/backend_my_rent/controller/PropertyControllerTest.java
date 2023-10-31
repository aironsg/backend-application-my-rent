package br.com.devairon.backend.backend_my_rent.controller;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.service.AddressService;
import br.com.devairon.backend.backend_my_rent.service.OwnerService;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestAddressDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestOwnerDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestPropertyDataGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:clear-database.sql"})
public class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private AddressService addressService;
    private PropertyDTO request;
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
        if (ownerResponse.isPresent()) {
            ownerService.createAddressProperty(addressDTO);
        }
        Optional<AddressEntity> addressById;
        addressById = addressService.getAddressById(1L).map(AddressEntity::new);
        request = TestPropertyDataGenerator.generatorRandomProperty(addressById);
    }

    @Test
    public void shouldCreatePropertyAndReturnStatusCodeCreated() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());
    }

    @Test
    public void shouldNotCreatePropertyAndReturnStatusCodeBadRequest() throws Exception {
        String content = objectMapper.writeValueAsString(new PropertyDTO());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetAllPropertiesAndReturnStatusCodeOK() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/property")
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldGetPropertiesByIdAndReturnStatusCodeOK() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/property/1")
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldNotGetPropertiesByIdInvalidAndReturnStatusCodeNotFound() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/property/123 ")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdatePropertyAndReturnStatusCodeCreated() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        String contentUpdated = objectMapper.writeValueAsString(TestPropertyDataGenerator.generatorRandomPropertyUpdate());
        mockMvc.perform(
                MockMvcRequestBuilders.put("/property/id/1")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(contentUpdated)
        ).andExpect(status().isCreated());
    }

    @Test
    public void shouldNotUpdatePropertyAndReturnStatusCodeNotFound() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        String contentUpdated = objectMapper.writeValueAsString(TestPropertyDataGenerator.generatorRandomPropertyUpdate());
        mockMvc.perform(
                MockMvcRequestBuilders.put("/property/id/23")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(contentUpdated)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void shouldInactivePropertyAndReturnStatusCodeCreated() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        String contentUpdated = objectMapper.writeValueAsString(TestPropertyDataGenerator.updateStatusPropertyForUNOCCUPIED());
        mockMvc.perform(
                MockMvcRequestBuilders.put("/property/inactive/id/1")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(contentUpdated)
        ).andExpect(status().isCreated());
    }
    @Test
    public void shouldNotInactivePropertyWithIdInvalidAndReturnStatusCodeNotFound() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        String contentUpdated = objectMapper.writeValueAsString(TestPropertyDataGenerator.updateStatusPropertyForUNOCCUPIED());
        mockMvc.perform(
                MockMvcRequestBuilders.put("/property/inactive/id/123")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(contentUpdated)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeletePropertyAndReturnStatusCodeNoContent() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/property/id/1")

        ).andExpect(status().isNoContent());
    }

    @Test
    public void shouldNotDeletePropertyWithIdInvalidAndReturnStatusCodeNoContent() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/property")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/property/id/123")

        ).andExpect(status().isNotFound());
    }




}
