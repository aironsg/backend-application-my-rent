package br.com.devairon.backend.backend_my_rent.controller;

import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.service.OwnerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService service;

    @Autowired
    ModelMapper mapper;

    @PostMapping
    public ResponseEntity<OwnerDTO> createOwner(@Valid @RequestBody OwnerDTO request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            Optional<OwnerDTO> responseWithEmailNULL = service.createOwner(request);
            return responseWithEmailNULL.map(owner -> ResponseEntity.status(HttpStatus.CREATED).body(owner))
                    .orElse(ResponseEntity.badRequest().build());
        }
        Optional<OwnerDTO> responseWithEmailNULL = service.createOwner(request);
        return responseWithEmailNULL.map(owner -> ResponseEntity.status(HttpStatus.CREATED).body(owner))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable Long id) {
        Optional<OwnerDTO> response = service.getOwnerById(id);
        return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<OwnerDTO> getOwnerByCPF(@PathVariable String cpf) {
        Optional<OwnerDTO> response = service.getOwnerByCPF(cpf);
        return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable Long id, OwnerDTO request) {
        Optional<OwnerDTO> response = service.updateOwner(id, request);
        return response.map(owner -> ResponseEntity.status(HttpStatus.CREATED).body(owner))
                .orElse(ResponseEntity.notFound().build());
    }
}
