package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.OwnerEntity;
import br.com.devairon.backend.backend_my_rent.repository.OwnerRepository;
import br.com.devairon.backend.backend_my_rent.usecase.OwnerUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService implements OwnerUseCase {

    @Autowired
    private OwnerRepository repository;
    @Autowired
    ModelMapper mapper;

    @Override
    public Optional<OwnerDTO> createOwner(OwnerDTO request) {
        if (areRequiredFieldsNotNull(request)){
            OwnerEntity owner = mapper.map(request, OwnerEntity.class);
            repository.save(owner);
            OwnerDTO response = mapper.map(request, OwnerDTO.class);
            return Optional.of(response);
        }
        return Optional.empty();
    }

    @Override
    public Optional<OwnerDTO> getOwnerById(Long id) {
        Optional<OwnerEntity> owner = repository.findById(String.valueOf(id));
        return owner.map(response -> mapper.map(response, OwnerDTO.class));
    }

    @Override
    public Optional<OwnerDTO> getOwnerByCPF(String cpf) {
        Optional<OwnerDTO> owner = repository.findByCpf(cpf);
        return owner.map(response -> mapper.map(response, OwnerDTO.class));

    }

    @Override
    public Optional<OwnerDTO> updateOwner(Long id, OwnerDTO request) {
        Optional<OwnerEntity> owner = repository.findById(String.valueOf(id));
        if (owner.isPresent() && (!request.getEmail().isEmpty() || !request.getEmail().isBlank())) {
            owner.get().setName(request.getName());
            owner.get().setEmail(request.getEmail());
            owner.get().setCpf(request.getCpf());
            owner.get().setPhoneNumber(request.getPhoneNumber());
            repository.save(owner.get());
            return Optional.of(mapper.map(owner, OwnerDTO.class));
        }

        if (owner.isPresent()) {
            owner.get().setName(request.getName());
            owner.get().setCpf(request.getCpf());
            owner.get().setPhoneNumber(request.getPhoneNumber());
            repository.save(owner.get());
            return Optional.of(mapper.map(owner, OwnerDTO.class));
        }
        return Optional.empty();
    }


    private boolean areRequiredFieldsNotNull(OwnerDTO request) {
        return request != null &&
                request.getName() != null &&
                request.getPhoneNumber() != null &&
                request.getCpf() != null &&
                request.getAddress() != null &&
                request.getTypePlan() != null &&
                request.getPlanStartDate() != null &&
                request.getPlanEndDate() != null;

    }
}
