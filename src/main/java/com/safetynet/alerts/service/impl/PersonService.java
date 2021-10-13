package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public List<Person> findPersonsByStationNumber(Integer stationNumber) {
        return personRepository.findPersonsByStationNumber(stationNumber);
    }


    @Override
    public List<Person> findPersonsByAddress(String address) {
        return personRepository.findPersonsByAddress(address);
    }

    @Override
    public List<String> findPhoneNumberByFireStation(Integer fireStationNumber) {
        return personRepository.findPhoneNumberByFireStation(fireStationNumber);
    }

    @Override
    public List<Person> findPersonsByStations(List<Integer> stations) {
        return personRepository.findPersonsByStations(stations);
    }

    @Override
    public List<Person> findAllByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findAllByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<String> findAllAddressMailsByCity(String city) {
        return personRepository.findAllAddressMailsByCity(city);
    }


    @Override
    public Person savePerson( Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        return personRepository.findById(id);
    }

    @Override
    public void deletePersons(List<Person> persons) {
        personRepository.deleteAll(persons);
    }


}