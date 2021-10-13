package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.impl.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @TestConfiguration
    static class PersonServiceImplTestContextConfiguration {

        @Bean
        public IPersonService personService() {
            return new PersonService();
        }
    }

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void whenFindPersonsByStationNumber_thenListOfPhonesShouldBeFound() {
        Mockito.when(personRepository.findPersonsByStationNumber(anyInt()))
                .thenReturn(ConstantsTest.persons);
        List<Person> persons = personService.findPersonsByStationNumber(1);
        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    public void whenFindPersonsByAddress_thenListOfPhonesShouldBeFound() {
        Mockito.when(personRepository.findPersonsByAddress(anyString()))
                .thenReturn(ConstantsTest.persons);
        List<Person> persons = personService.findPersonsByAddress("address");
        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    public void whenFindPhoneNumberByFireStation_thenListOfPhonesShouldBeFound() {
        Mockito.when(personRepository.findPhoneNumberByFireStation(anyInt()))
                .thenReturn(ConstantsTest.phones);
        List<String> phones = personService.findPhoneNumberByFireStation(1);
        assertNotNull(phones);
        assertEquals(2, phones.size());
    }

    @Test
    public void whenFindPersonsByStations_thenListOfPhonesShouldBeFound() {
        Mockito.when(personRepository.findPersonsByStations(anyList()))
                .thenReturn(ConstantsTest.persons);
        List<Person> persons = personService.findPersonsByStations(Collections.singletonList(1));
        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    public void whenFindAllByFirstNameAndLastName_thenListOfPhonesShouldBeFound() {
        Mockito.when(personRepository.findAllByFirstNameAndLastName(anyString(),anyString()))
                .thenReturn(ConstantsTest.persons);
        List<Person> persons = personService.findAllByFirstNameAndLastName("fisrtName","lastName");
        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    public void whenFindAllAddressMailsByCity_thenListOfPhonesShouldBeFound() {
        Mockito.when(personRepository.findAllAddressMailsByCity(anyString()))
                .thenReturn(ConstantsTest.emails);
        List<String> emails = personService.findAllAddressMailsByCity("city");
        assertNotNull(emails);
        assertEquals(3, emails.size());
    }

    @Test
    public void whenSavePerson_thenPersonShouldBeSaved() {
        Mockito.when(personRepository.save(any()))
                .thenReturn(ConstantsTest.person);
        Person person = personService.savePerson(new Person());
        assertNotNull(person);
        assertEquals(person.getId(), 1);
    }

    @Test
    public void whenGetPersonById_thenPersonShouldBeFound() {
        Mockito.when(personRepository.findById(anyInt()))
                .thenReturn(Optional.of(ConstantsTest.person));
        Optional<Person> person = personService.getPersonById(1);
        assertNotNull(person);
        assertTrue(person.isPresent());
        assertEquals(person.get().getId(), 1);
    }


    @Test
    public void whenDeletePersons_thenPersonsShouldBeDeleted() {
        doNothing().when(personRepository).deleteAll(any());
        personService.deletePersons(ConstantsTest.persons);
        verify(personRepository,times(1)).deleteAll(any());
        assertDoesNotThrow(() -> personService.deletePersons(any()));
    }


}
