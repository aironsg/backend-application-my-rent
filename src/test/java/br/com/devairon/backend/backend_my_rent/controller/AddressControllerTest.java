package br.com.devairon.backend.backend_my_rent.controller;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.teste_data_generator.TestAddressDataGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressController controller;

    @Autowired
    private ObjectMapper mapper;

    private AddressDTO request;

    @BeforeEach
    public void setUp(){
        request = TestAddressDataGenerator.generatorRandomAddress();
    }

    @Test
    public void shouldCreateAddressAndReturnStatusCodeCreated() throws Exception {
        String content = mapper.writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/address")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());
    }

    @Test
    public void shouldCreateAddressWithFieldZipCodeInBlankAndReturnStatusCodeCreated() throws Exception {
        String content = mapper.writeValueAsString(TestAddressDataGenerator.generatorRandomAddressWithFieldZipCodeBlank());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/address")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());
    }

    @Test
    public void shouldNotCreateAddressWithFieldsInBlankOrNullAndReturnStatusCodeBadRequest() throws Exception {
        AddressDTO addressDTO = new AddressDTO();
        String content = mapper.writeValueAsString(addressDTO);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/address")
                        .header(AUTHORIZATION, "Bearer ")
                        .contentType(APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isBadRequest());
    }


    @Test
    public void shouldGetAllAddressesAndReturnStatusCodeOK() throws Exception {
        String content = mapper.writeValueAsString(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/address")
                .header(AUTHORIZATION, "Bearer ")
                .contentType(APPLICATION_JSON)
                .content(content)
        );

        ResponseEntity<List<AddressDTO>> allAddress = controller.getAllAddress();
        mockMvc.perform(MockMvcRequestBuilders.get("/address")
                ).andExpect(status().isOk());
    }
    @Test
    public void shouldReturnListEmptyIfDataBaseIsEmptyAndStatusCodeOK() throws Exception {
        ResponseEntity<List<AddressDTO>> allAddress = controller.getAllAddress();
        mockMvc.perform(MockMvcRequestBuilders.get("/address")
        ).andExpect(status().isOk());
       assertTrue(allAddress.getBody().isEmpty());

    }


}
