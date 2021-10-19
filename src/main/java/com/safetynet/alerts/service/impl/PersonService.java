package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.IPersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {


    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findPersonsByStationNumber(Integer stationNumber) {
        return personRepository.findPersonsByStationNumber(stationNumber);
    }


    @Override
    public List<Person> findPersonsByAddress(String address) {
        return personRepository.findPersonsByAddress(address);
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
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person sourcePerson, Person targetPerson) {
        BeanUtils.copyProperties(sourcePerson, targetPerson, "id", "firstName", "lastName");
        return personRepository.save(targetPerson);
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