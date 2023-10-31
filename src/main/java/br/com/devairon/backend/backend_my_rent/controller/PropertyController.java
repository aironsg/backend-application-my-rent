package br.com.devairon.backend.backend_my_rent.controller;

import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;
import br.com.devairon.backend.backend_my_rent.service.PropertyService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@Valid @RequestBody PropertyDTO request) {
        //TODO:fazer o tratamento de exceção customizada antes de enviar o dado para o service
        Optional<PropertyDTO> response = service.createProperty(request);
        return response.map(property -> ResponseEntity.status(HttpStatus.CREATED).body(property))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
      return ResponseEntity.ok( service.getAllProperties());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id){
        Optional<PropertyDTO> response = service.getPropertyById(id);
        return response.map(property -> ResponseEntity.status(HttpStatus.OK).body(property))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id,@Valid @RequestBody PropertyDTO request){
        Optional<PropertyDTO> response = service.updateProperty(id, request);
        return response.map(property -> ResponseEntity.status(HttpStatus.CREATED).body(property))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/inactive/id/{id}")
    public ResponseEntity<Boolean> inactiveProperty(@PathVariable Long id){
        Optional<Boolean> response = service.inactiveProperty(id);
        if(response.get()){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteProperty(@PathVariable Long id){
        Optional<Boolean> response = service.deleteProperty(id);
        if(response.get()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
