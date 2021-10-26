package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.PersonMedicalRecordDTO;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.util.CalculateAge;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService implements IPersonService {


    private final Data data;

    public PersonService( Data data) {
        this.data = data;
    }


    @Override
    public List<PersonMedicalRecordDTO> findAllByFirstNameAndLastName(String firstName, String lastName) {

        List<PersonMedicalRecordDTO> personMedicalRecordDTOS = data.getPersons().stream().
                filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)).
                map(p -> {
                    PersonMedicalRecordDTO personMedicalRecordDTO = new PersonMedicalRecordDTO();
                    personMedicalRecordDTO.setPerson(p);
                    personMedicalRecordDTO.setMedicalRecord(data.getMedicalrecords().stream().
                            filter(m -> m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())
                            ).findFirst().orElse(null));
                    // calculate age
                    personMedicalRecordDTO.getPerson().setAge(personMedicalRecordDTO.getMedicalRecord() != null ? CalculateAge.calculateAge(personMedicalRecordDTO.getMedicalRecord().getBirthdate()) : null);
                    return personMedicalRecordDTO;
                }).collect(Collectors.toList());

        return personMedicalRecordDTOS;

    }


    @Override
    public Person createPerson(com.safetynet.alerts.model.Person person) {
        data.getPersons().add(person);
        return person;
    }

    @Override
    public Person updatePerson(Person person) {
        data.getPersons().forEach( p -> {
                if (p.getLastName().equals(person.getLastName()) && p.getFirstName().equals(person.getFirstName())){
                    BeanUtils.copyProperties(person, p, "firstName", "lastName");
                }
                }
        );
        return person;
    }

    @Override
    public void deletePerson(String firstName, String lastName) {
        data.getPersons().removeIf(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName));
    }

    @Override
    public boolean existPersonByFirstNameAndLastName(String firstName, String lastName) {
        return data.getPersons().stream().anyMatch(p -> p.getLastName().equals(lastName) && p.getFirstName().equals(firstName));
    }
}