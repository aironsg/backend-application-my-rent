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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @BeforeEach
    public void setUp() {
        address = TestAddressDataGenerator.generatorRandomAddress();
        addressService.createAddress(address);
        Optional<AddressDTO> addressResponse = addressService.getAddressById(1L);
        AddressEntity addressEntity = mapper.map(addressResponse, AddressEntity.class);
        request = TestOwnerDataGenerator.generatorRandomOwner(addressEntity);
        requestUpdate = TestOwnerDataGenerator.generatorRandomOwner(addressEntity);
        requestCPFInvalid = TestOwnerDataGenerator.generatorRandomOwnerWithCPFInvalid(addressEntity);
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
    public void shouldNotCreateOwnerWithCPFInvalidAndReturnStatusCodeBadRequest() throws Exception {
        String content = objectMapper.writeValueAsString(requestCPFInvalid);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/owner")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isBadRequest());
    }
}
