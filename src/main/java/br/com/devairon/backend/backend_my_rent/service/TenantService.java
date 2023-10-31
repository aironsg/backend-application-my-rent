package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.TenantDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.TenantEntity;
import br.com.devairon.backend.backend_my_rent.repository.TenantRepository;
import br.com.devairon.backend.backend_my_rent.usecase.TenantUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService implements TenantUseCase {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TenantRepository repository;

    @Override
    public Optional<TenantDTO> createTenant(TenantDTO request) {
        if (areRequiredFieldsNotNull(request)) {
            TenantEntity tenant = mapper.map(request, TenantEntity.class);
            repository.save(tenant);
            return Optional.of(mapper.map(tenant, TenantDTO.class));

        }
        return Optional.empty();
    }

    @Override
    public Optional<TenantDTO> getTenantByCPF(String cpf) {
        Optional<TenantDTO> response = repository.findByCpf(cpf);
        if(response.isPresent()){
            return Optional.of(response.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<TenantDTO>> getAllTenants() {
        List<TenantEntity> tenantEntityList = repository.findAll();
        List<TenantDTO> tenantDTOList = tenantEntityList.stream()
                .map(tenant -> mapper.map(tenant, TenantDTO.class))
                .toList();
        return Optional.of(tenantDTOList);
    }

    @Override
    public Optional<Boolean> updateTenant(Long id, TenantDTO request) {
        Optional<TenantEntity> response = repository.findById(String.valueOf(id));
        if(response.isPresent()){
            TenantEntity tenant = mapper.map(response,TenantEntity.class);
            if(request.getQuantityDependents() != tenant.getQuantityDepedents()){
                tenant.setQuantityDepedents(request.getQuantityDependents());
            }
            if(!request.getCpf().equals(tenant.getCpf())){
                tenant.setCpf(request.getCpf());
            }
            if(!request.getName().equals(tenant.getName())){
                tenant.setName(request.getName());
            }
            if(!request.getPhoneNumber().equals(tenant.getPhoneNumber())){
                tenant.setPhoneNumber(request.getPhoneNumber());
            }
            if(!request.getEmail().equals(tenant.getEmail())){
                tenant.setEmail(request.getEmail());
            }
            repository.save(tenant);
            return Optional.of(true);
        }
        return Optional.of(false);
    }

    @Override
    public Optional<Boolean> deleteTenant(Long id) {
        Optional<TenantEntity> response = repository.findById(String.valueOf(id));
        if(response.isPresent()){
            repository.delete(response.get());
            return Optional.of(true);
        }
        return Optional.of(false);
    }


    private boolean areRequiredFieldsNotNull(TenantDTO request) {
        return request != null &&
                request.getProperty() != null &&
                request.getCpf() != null &&
                request.getPhoneNumber() != null &&
                request.getRentDate() != null &&
                request.getName() != null;
    }
}
