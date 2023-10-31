package br.com.devairon.backend.backend_my_rent.controller;

import br.com.devairon.backend.backend_my_rent.domain.dto.TenantDTO;
import br.com.devairon.backend.backend_my_rent.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    private TenantService service;

    @PostMapping
    public ResponseEntity<TenantDTO> createTenant(@Valid @RequestBody TenantDTO request){
        Optional<TenantDTO> response = service.createTenant(request);
        return response.map(tenant -> ResponseEntity.status(HttpStatus.CREATED).body(tenant))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<TenantDTO> getTenantByCPF(@PathVariable String cpf){
        //TODO: analisar se trocar para um @requestParam não seja melhor.
        Optional<TenantDTO> response = service.getTenantByCPF(cpf);
        return response.map(tenant -> ResponseEntity.status(HttpStatus.OK).body(tenant))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TenantDTO>> getAllTenants(){
       return ResponseEntity.ok(service.getAllTenants().get());
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Boolean> updateTenant(@PathVariable Long id, @RequestBody TenantDTO request){
        Optional<Boolean> response = service.updateTenant(id, request);
        if(response.get()){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteTenant(@PathVariable Long id){
        Optional<Boolean> response = service.deleteTenant(id);
        if(response.get()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
