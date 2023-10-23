package br.com.devairon.backend.backend_my_rent.controller;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.service.AddressService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService service;

    @Autowired
    ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddress() {
        return ResponseEntity.ok(service.getAllAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Optional<AddressDTO> response = service.getAddressById(id);
        return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody @Valid  AddressDTO request) {
        if (request.getZipCode() == null) {
            Optional<AddressDTO> responseWithZipCodeNULL = service.createAddressWithFieldZipCodeBlank(request);
            return responseWithZipCodeNULL.map(address -> ResponseEntity.status(HttpStatus.CREATED).body(address))
                    .orElse(ResponseEntity.badRequest().build());
        }
        Optional<AddressDTO> response = service.createAddress(request);
        return response.map(address -> ResponseEntity.status(HttpStatus.CREATED).body(address))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddressById(@PathVariable Long id, @Valid @RequestBody AddressDTO request) {
        Optional<AddressDTO> response = service.updateAddressById(request, id);
        return response.map(address -> ResponseEntity.status(HttpStatus.CREATED).body(address))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddressProperty(@PathVariable Long id){
        boolean deleted = service.deleteAddressProperty(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
