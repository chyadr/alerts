package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    List<Person> findPersonsByStationNumber(Integer StationNumber);
    List<Person> findPersonsByAddress( String address);
    List<String> findPhoneNumberByFireStation( Integer fireStationNumber);
    List<Person> findPersonsByStations(List<Integer> stations);
    List<Person> findAllByFirstNameAndLastName(String firstName, String lastName);
    List<String> findAllAddressMailsByCity(String city);
    Person savePerson(Person person);
    Optional<Person> getPersonById(int id);
    void deletePersons(List<Person> persons);

}
