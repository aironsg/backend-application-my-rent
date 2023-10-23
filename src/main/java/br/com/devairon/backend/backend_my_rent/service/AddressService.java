package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.AddressDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.AddressEntity;
import br.com.devairon.backend.backend_my_rent.repository.AddressRepository;
import br.com.devairon.backend.backend_my_rent.usecase.AddressUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AddressService implements AddressUseCase {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public Optional<AddressDTO> createAddress(AddressDTO request) {
     if(areRequiredFieldsNotNull(request)){
         AddressEntity address = mapper.map(request, AddressEntity.class);
         repository.save(address);
         AddressDTO response = mapper.map(request, AddressDTO.class);
         return Optional.of(response);
     }
     return Optional.empty();
    }

    private boolean areRequiredFieldsNotNull(AddressDTO request) {
        return request != null &&
                request.getStreet() != null &&
                request.getCity() != null &&
                request.getState() != null;

    }

    @Override
    public Optional<AddressDTO> createAddressWithFieldZipCodeBlank(AddressDTO request) {
        AddressEntity address = mapper.map(request, AddressEntity.class);
        repository.save(address);
        AddressDTO response = mapper.map(request, AddressDTO.class);
        return Optional.of(response);
    }

    @Override
    public Optional<AddressDTO> updateAddressById(AddressDTO request,Long id) {
        Optional<AddressEntity> address = repository.findById(String.valueOf(id));
        if (address.isPresent()) {
            address.get().setState(request.getZipCode());
            address.get().setState(request.getState());
            address.get().setState(request.getUF());
            address.get().setState(request.getCity());
            address.get().setState(request.getNeighborhood());
            address.get().setState(request.getStreet());
            address.get().setState(request.getNumbe());
            repository.save(address.get());
            return Optional.of(mapper.map(address, AddressDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AddressDTO> getAddressById(Long id) {

           Optional<AddressEntity> address = repository.findById(String.valueOf(id));
           return address.map(response -> mapper.map(response, AddressDTO.class));

    }

    @Override
    public List<AddressDTO> getAllAddress() {
        List<AddressEntity> addresses = repository.findAll();
        List<AddressDTO> responses = new ArrayList<AddressDTO>();
        addresses.forEach(address -> {
            AddressDTO addressDTO = mapper.map(address, AddressDTO.class);
            responses.add(addressDTO);
        });
        return responses;
    }



    @Override
    public boolean deleteAddressProperty(Long id) {
        Optional<AddressEntity> response = repository.findById(String.valueOf(id));
        if(response.isPresent()) {
            repository.deleteById(String.valueOf(response.get().getId()));
            return true;
        }
        return false;
    }


}
