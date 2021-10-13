package com.safetynet.alerts.mapper;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.model.Person;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;

public class PersonMapper {


    public static PersonDTO mapPerson(Person person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setPhone(person.getPhone());
        personDTO.setEmail(person.getEmail());
        personDTO.setBirthdate(person.getBirthdate());
        if (person.getBirthdate()!= null) {
            personDTO.setAge(Period.between(person.getBirthdate().toInstant()
                    .atZone(ZoneId.of("UTC"))
                    .toLocalDate(), LocalDate.now()).getYears());
        }
        if (person.getAddress() != null) {
            personDTO.setAddressDTO(AddressMapper.mapAddress(person.getAddress()));
        }
        //map medical record/allergy/medication
        if(person.getMedicalRecord()!=null){
            personDTO.setMedicalRecordsDTO(MedicalRecordMapper.mapMedicalRecord(person.getMedicalRecord()));
        }
        return personDTO;
    }





}
