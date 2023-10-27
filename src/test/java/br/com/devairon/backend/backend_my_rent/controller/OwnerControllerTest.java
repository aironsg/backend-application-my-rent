package br.com.devairon.backend.backend_my_rent.controller;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.service.AddressService;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestAddressDataGenerator;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestOwnerDataGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressController addressController;
    @Autowired
    private OwnerController controller;

    AddressDTO address;

    private OwnerDTO request;
    private OwnerDTO requestUpdate;
    private OwnerDTO requestCPFInvalid;
    private OwnerDTO requestEmailNULL;

    @BeforeEach
    public void setUp() {
        address = TestAddressDataGenerator.generatorRandomAddress();
        addressService.createAddress(address);
        Optional<AddressDTO> addressResponse = addressService.getAddressById(1L);
        AddressEntity addressEntity = mapper.map(addressResponse, AddressEntity.class);
        request = TestOwnerDataGenerator.generatorRandomOwner();
        requestUpdate = TestOwnerDataGenerator.generatorRandomOwnerUpdate();
        requestCPFInvalid = TestOwnerDataGenerator.generatorRandomOwnerWithCPFInvalid();
        requestEmailNULL = TestOwnerDataGenerator.generatorRandomOwnerWithEmailNull();
    }

    @Test
    public void shouldCreateOwnerAndReturnStatusCodeCreated() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());
    }

    @Test
    public void shouldCreateAddressPropertyAndReturnStatusCodeCreated() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        String contentAddress = objectMapper.writeValueAsString(address);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner/address")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(contentAddress)
        ).andExpect(status().isCreated());
    }
    @Test
    public void shouldNotCreateAddressPropertyAndReturnStatusCodeCreated() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());
        AddressDTO addressNull = new AddressDTO();
        String contentAddress = objectMapper.writeValueAsString(addressNull);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner/address")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(contentAddress)
        ).andExpect(status().isBadRequest());
    }
    @Test
    public void shouldCreateOwnerWithEmailNullAndReturnStatusCodeCreated() throws Exception {
        String content = objectMapper.writeValueAsString(requestEmailNULL);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());
    }
    @Test
    public void shouldNotCreateOwnerWithCPFInvalidAndReturnStatusCodeBadRequest() throws Exception {
        String content = objectMapper.writeValueAsString(requestCPFInvalid);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetOwnerByIdAndReturnStatusCodeOK() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/owner/id/1")).andExpect(status().isOk());
    }

    @Test
    public void shouldNotGetOwnerByIdAndReturnStatusCodeNotFound() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/owner/id/2")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetOwnerByCPFAndReturnStatusCodeOK() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/owner/cpf/44866522070")).andExpect(status().isOk());
    }
    @Test
    public void shouldNotGetOwnerByCPFAndReturnStatusCodeNotFound() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/owner/cpf/448665220700")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateOwnerAndReturnStatusCodeCreated() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        content =  objectMapper.writeValueAsString(requestUpdate);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/owner/1")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateEmailOwnerAndReturnStatusCodeCreated() throws Exception {
        String content = objectMapper.writeValueAsString(requestEmailNULL);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        content =  objectMapper.writeValueAsString(requestUpdate);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/owner/1")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());
    }

    @Test
    public void shouldNotUpdateOwnerWithIdInvalidAndReturnStatusCodeNotFound() throws Exception {
        String content = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());

        content =  objectMapper.writeValueAsString(requestUpdate);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/owner/2")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isNotFound());
    }

}
