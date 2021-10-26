package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonMedicalRecordDTO;
import com.safetynet.alerts.model.Person;

import java.util.List;

public interface IPersonService {


    List<PersonMedicalRecordDTO> findAllByFirstNameAndLastName(String firstName, String lastName);

    boolean existPersonByFirstNameAndLastName (String firstName, String lastName);

    com.safetynet.alerts.model.Person createPerson(com.safetynet.alerts.model.Person person);

    Person updatePerson(com.safetynet.alerts.model.Person person);

    void deletePerson(String firstName, String lastName);

}
