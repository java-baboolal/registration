package com.champion.academy.regististion.services.impl;

import com.champion.academy.regististion.dto.ContactDTO;
import com.champion.academy.regististion.dto.RegistrationDTO;
import com.champion.academy.regististion.entities.Contact;
import com.champion.academy.regististion.entities.RegistrationEntity;
import com.champion.academy.regististion.exceptions.AdequateInputNotFound;
import com.champion.academy.regististion.exceptions.UserNotFoundException;
import com.champion.academy.regististion.repositories.RegistrationRepository;
import com.champion.academy.regististion.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImp implements RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    ConfigurableEnvironment env;

    @Override
    public ResponseEntity<String> registerUser(RegistrationDTO registration) {

        RegistrationEntity registrationEntity = transformDtoToEntity(registration);
        registrationRepository.save(registrationEntity);
        return new ResponseEntity<>("User created successfully!", HttpStatus.CREATED);
    }

    @Override
    public RegistrationDTO findByUserName(Long id) throws EntityNotFoundException {
        RegistrationEntity registrationEntity = registrationRepository.getOne(id);
        return transformEntityToDto(registrationEntity);
    }

    @Override
    public RegistrationDTO findByUserName(Long id, String email) {
        RegistrationEntity entity = null;
        if (id == null && email == null) {
            throw new AdequateInputNotFound(env.getProperty("input.not.sufficient"));
        } else if (id != null && email != null) {
            entity = registrationRepository.findByIdAndEmail(id, email);
        } else if (id != null) {
            entity = registrationRepository.getOne(id);
        } else {
            entity = registrationRepository.findByEmail(email);
        }
        if (entity == null)
            throw new UserNotFoundException(env.getProperty("user.not.found"));
        return transformEntityToDto(entity);
    }

    @Override
    public List<RegistrationDTO> findAllUsers() {
        List<RegistrationEntity> listRegs = registrationRepository.findByContactCityOrderByFirstNameDesc("noida");
        return transformToDtos.apply(listRegs);
    }

    @Override
    public List<RegistrationDTO> findAllUsersPageWise(Integer pageNumber, Integer size) {
        PageRequest pageRequest = new PageRequest(pageNumber, size);
        List<RegistrationEntity> listRegs = registrationRepository.findByContactCityOrderByFirstNameDesc("Noida", pageRequest);
        return transformToDtos.apply(listRegs);
    }

    private RegistrationEntity transformDtoToEntity(RegistrationDTO registration) {
        RegistrationEntity registrationEntity = new RegistrationEntity();
        registrationEntity.setUserName(registration.getUserName());
        registrationEntity.setFirstName(registration.getFirstName());
        registrationEntity.setLastName(registration.getLastName());
        registrationEntity.setEmail(registration.getEmail());
        Contact contact = new Contact();
        contact.setContactNumber(registration.getContactDTO().getContactNumber());
        contact.setAddress(registration.getContactDTO().getAddress());
        contact.setCity(registration.getContactDTO().getCity());
        contact.setState(registration.getContactDTO().getState());
        contact.setPincode(registration.getContactDTO().getPincode());
        registrationEntity.setContact(contact);
        return registrationEntity;
    }

    private RegistrationDTO transformEntityToDto(RegistrationEntity registration) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setId(registration.getId());
        registrationDTO.setUserName(registration.getUserName());
        registrationDTO.setFirstName(registration.getFirstName());
        registrationDTO.setLastName(registration.getLastName());
        registrationDTO.setEmail(registration.getEmail());

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setContactNumber(registration.getContact().getContactNumber());
        contactDTO.setAddress(registration.getContact().getAddress());
        contactDTO.setCity(registration.getContact().getCity());
        contactDTO.setState(registration.getContact().getState());
        contactDTO.setPincode(registration.getContact().getPincode());

        registrationDTO.setContactDTO(contactDTO);
        return registrationDTO;
    }

    private Function<List<RegistrationEntity>, List<RegistrationDTO>> transformToDtos = (registrations) -> {
        return registrations.stream()
                .map(registration -> transformEntityToDto(registration))
                .collect(Collectors.toList());
    };
    // public Function<List<RegistrationEntity>, List<RegistrationDTO>> transformDto

    /*public Function<List<RegistrationDTO>, List<RegistrationEntity>> getEntitiesFromDtos(List<RegistrationDTO> registrationDTOList)->{ registrationDTOList
                .stream()
                .map(registration -> transformDtoToEntity(registration))
                .collect(Collectors.toList());
    }*/
}
