package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.dto.PersonMedicalRecordDTO;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.impl.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private Data data;

    @Test
    public void whenFindAllByFirstNameAndLastName_thenListOfPersonMedicalRecordDTOShouldBeFound() {
        Mockito.when(data.getPersons()).thenReturn(ConstantsTest.persons);
        Mockito.when(data.getMedicalrecords()).thenReturn(ConstantsTest.medicalRecords);

        List<PersonMedicalRecordDTO> medicalRecordDTOS = personService.findAllByFirstNameAndLastName("John", "Boyd");
        assertNotNull(medicalRecordDTOS);
        assertEquals(1, medicalRecordDTOS.size());
    }

    @Test
    public void whenCreatePerson_thenPersonShouldBeSaved() {

        Person person = personService.createPerson(new Person());
        assertNotNull(person);

    }

    @Test
    public void whenExistPersonByFirstNameAndLastName_thenPersonShouldBeExisted() {
        Mockito.when(data.getPersons()).thenReturn(ConstantsTest.persons);
        boolean exist = personService.existPersonByFirstNameAndLastName("John","Boyd");
        assertTrue(exist);

    }

    @Test
    public void whenUpdatePerson_thenPersonShouldBeUpdated() {
        Mockito.when(data.getPersons()).thenReturn(ConstantsTest.persons);
        Person person = personService.updatePerson(new Person().firstName("xxx").lastName("yyy").zip(20000));
        assertNotNull(person);
        assertEquals(20000,person.getZip());

    }



    @Test
    public void whenDeletePersons_thenPersonsShouldBeDeleted() {
        Mockito.when(data.getPersons()).thenReturn(ConstantsTest.persons);
        assertDoesNotThrow(() -> personService.deletePerson("delete","delete"));
    }


}