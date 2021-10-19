package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    List<Person> findPersonsByStationNumber(Integer StationNumber);

    List<Person> findPersonsByAddress(String address);

    List<Person> findPersonsByStations(List<Integer> stations);

    List<Person> findAllByFirstNameAndLastName(String firstName, String lastName);

    Person createPerson(Person person);

    Person updatePerson(Person sourcePerson, Person targetPerson);

    Optional<Person> getPersonById(int id);

    void deletePersons(List<Person> persons);

}
