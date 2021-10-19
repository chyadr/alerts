package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.IPhoneAlertService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneAlertService implements IPhoneAlertService {


    private final PersonRepository personRepository;

    public PhoneAlertService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<String> findPhoneNumberByFireStation(Integer fireStationNumber) {
        return personRepository.findPhoneNumberByFireStation(fireStationNumber);
    }
}
