package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.ICommunityEmailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityEmailService implements ICommunityEmailService {


    private final PersonRepository personRepository;

    public CommunityEmailService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<String> findAllAddressMailsByCity(String city) {
        return personRepository.findAllAddressMailsByCity(city);
    }
}
