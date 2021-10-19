package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.dto.PersonInfosDTO;
import com.safetynet.alerts.mapper.PersonMapper;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.IFireStationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FireStationService implements IFireStationService {

    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;

    public FireStationService(PersonRepository personRepository, FireStationRepository fireStationRepository) {
        this.personRepository = personRepository;
        this.fireStationRepository = fireStationRepository;
    }

    @Override
    public FireStation createFireStation(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    @Override
    public FireStation saveFireStation(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    @Override
    public Optional<FireStation> findFireStationById(int id) {
        return fireStationRepository.findById(id);
    }

    @Override
    public void deleteFireStation(FireStation fireStation) {
        fireStationRepository.delete(fireStation);
    }

    @Override
    public List<FireStation> findFireStationsByAddressId(int id) {
        return fireStationRepository.findFireStationsByAddressId(id);
    }

    @Override
    public PersonInfosDTO findPersonsByStationNumber(Integer stationNumber) {
        PersonInfosDTO personInfosDTO = new PersonInfosDTO();
        List<Person> persons = personRepository.findPersonsByStationNumber(stationNumber);
        List<PersonDTO> personDTOS = persons.stream().map(PersonMapper::mapPerson).collect(Collectors.toList());
        personInfosDTO.setPersonDTOS(personDTOS);
        personInfosDTO.setNumberOfAdult((personDTOS.stream().filter(p -> p.getAge() >= 18).count()));
        personInfosDTO.setNumberOfChild(personDTOS.size() - personInfosDTO.getNumberOfAdult());
        return personInfosDTO;
    }


}





