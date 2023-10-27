package br.com.devairon.backend.backend_my_rent.service;

import br.com.devairon.backend.backend_my_rent.domain.dto.OwnerDTO;
import br.com.devairon.backend.backend_my_rent.domain.dto.PropertyDTO;
import br.com.devairon.backend.backend_my_rent.domain.entity.PropertyEntity;
import br.com.devairon.backend.backend_my_rent.domain.enums.OccupationStatus;
import br.com.devairon.backend.backend_my_rent.repository.PropertyRepository;
import br.com.devairon.backend.backend_my_rent.usecase.PropertyUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PropertyService implements PropertyUseCase {

    @Autowired
    private PropertyRepository repository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Optional<PropertyDTO> createProperty(PropertyDTO request)  {
        if (areRequiredFieldsNotNull(request)) {
            PropertyEntity property = mapper.map(request, PropertyEntity.class);
            PropertyEntity response = repository.save(property);
            return Optional.of(mapper.map(response, PropertyDTO.class));
        }
        return Optional.empty();

    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyDTO> propertyDTOS = new ArrayList<>();
        List<PropertyEntity> properties = repository.findAll();
        for (PropertyEntity property : properties) {
            propertyDTOS.add(mapper.map(property, PropertyDTO.class));
        }
        return propertyDTOS;
    }

    @Override
    public Optional<PropertyDTO> getPropertyById(Long id) {
        Optional<PropertyEntity> property = repository.findById(String.valueOf(id));
        if (property.isPresent()) {
            return Optional.of(mapper.map(property, PropertyDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PropertyDTO> updateProperty(Long id, PropertyDTO request) {
        Optional<PropertyEntity> property = repository.findById(String.valueOf(id));
        if (property.isPresent()) {
            PropertyEntity propertyEntity = mapper.map(property, PropertyEntity.class);
            if (request.getQuantityRooms() != property.get().getQuantityRooms()) {
                propertyEntity.setQuantityRooms(request.getQuantityRooms());
            }
            if (request.getDescription() != property.get().getDescription()) {
                propertyEntity.setDescription(request.getDescription());
            }
            if (request.getAddressProperty() != property.get().getAddressProperty()) {
                propertyEntity.setAddressProperty(request.getAddressProperty());
            }
            if (request.getOccupationStatus() != property.get().getOccupationStatus()) {
                propertyEntity.setOccupationStatus(request.getOccupationStatus());
            }
            if (request.getRentValue() != property.get().getRentValue()) {
                propertyEntity.setRentValue(request.getRentValue());
            }
            PropertyEntity response = repository.save(propertyEntity);
            Optional.of(mapper.map(response, PropertyDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PropertyDTO> deleteProperty(Long id) {
        //TODO: implement deleteProperty
        Optional<PropertyEntity> property = repository.findById(String.valueOf(id));
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> inactiveProperty(Long id) {
        Optional<PropertyEntity> property = repository.findById(String.valueOf(id));
        if (property.isPresent()) {
            property.get().setOccupationStatus(OccupationStatus.UNOCCUPIED);
            repository.save(mapper.map(property, PropertyEntity.class));
            Optional.of(true);
        }
        return Optional.of(false);
    }

    private boolean areRequiredFieldsNotNull(PropertyDTO request) {
        return request != null &&
                request.getDescription() != null &&
                request.getOccupationStatus() != null &&
                request.getAddressProperty() != null;
    }
}
