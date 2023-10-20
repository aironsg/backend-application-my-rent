package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.repository.AddressRepository;
import br.com.devairon.backend.backend_my_rent.usecase.AddressUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements AddressUseCase {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public Optional<AddressDTO> createAddressOwner(AddressDTO request) {
     if(request != null){
         AddressEntity address = mapper.map(request, AddressEntity.class);
         repository.save(address);
         AddressDTO response = mapper.map(request, AddressDTO.class);
         return Optional.of(response);
     }
     return Optional.ofNullable(request);
    }

    @Override
    public Optional<AddressDTO> createAddressOwnerWithFieldZipCodeBlank(AddressDTO request) {
        AddressEntity address = mapper.map(request, AddressEntity.class);
        repository.save(address);
        AddressDTO response = mapper.map(request, AddressDTO.class);
        return Optional.of(response);
    }
    @Override
    public Optional<AddressDTO> createAddressProperty(AddressDTO request) {
        return Optional.empty();
    }

}
