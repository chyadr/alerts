package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.impl.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
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
    public void whenFindPersonsByStations_thenListOfPhonesShouldBeFound() {
        Mockito.when(personRepository.findPersonsByStations(anyList()))
                .thenReturn(ConstantsTest.persons);
        List<Person> persons = personService.findPersonsByStations(Collections.singletonList(1));
        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    public void whenFindAllByFirstNameAndLastName_thenListOfPhonesShouldBeFound() {
        Mockito.when(personRepository.findAllByFirstNameAndLastName(anyString(), anyString()))
                .thenReturn(ConstantsTest.persons);
        List<Person> persons = personService.findAllByFirstNameAndLastName("fisrtName", "lastName");
        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    public void whenCreatePerson_thenPersonShouldBeSaved() {
        Mockito.when(personRepository.save(any()))
                .thenReturn(ConstantsTest.person);
        Person person = personService.createPerson(new Person());
        assertNotNull(person);
        assertEquals(person.getId(), 1);
    }

    @Test
    public void whenUpdatePerson_thenPersonShouldBeSaved() {
        Mockito.when(personRepository.save(any()))
                .thenReturn(ConstantsTest.person);
        Person person = personService.updatePerson(new Person(), new Person());
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
        verify(personRepository, times(1)).deleteAll(any());
        assertDoesNotThrow(() -> personService.deletePersons(any()));
    }


}
