package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.dto.PersonFireStationNumberDTO;
import com.safetynet.alerts.mapper.PersonMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.IFireService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireService implements IFireService {

    private final PersonRepository personRepository;

    public FireService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonFireStationNumberDTO findPersonFireStationNumber(String address) {

        PersonFireStationNumberDTO personFireStationNumberDTO = new PersonFireStationNumberDTO();
        List<Person> persons = personRepository.findPersonsByAddress(address);
        List<PersonDTO> personDTOS = persons.stream().map(PersonMapper::mapPerson).collect(Collectors.toList());

        personFireStationNumberDTO.setStationNumber(persons.stream().findFirst().get().getAddress().getFireStation().getStation());
        personFireStationNumberDTO.setPersonDTOS(personDTOS);

        return personFireStationNumberDTO;
    }
}
