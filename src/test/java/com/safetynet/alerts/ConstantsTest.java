package com.safetynet.alerts;

import com.safetynet.alerts.model.*;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

public final class ConstantsTest {

    public static final List<String> phones = Arrays.asList("841-874-6512", "841-874-6513");

    public static final List<Person> personsInfos = Collections.singletonList(new Person().firstName("John").lastName("Boyd"));

    public static final List<Person> persons = Arrays.asList(new Person().firstName("John").lastName("Boyd").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))), new Person().firstName("Jacob").lastName("Boyd").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))).medicalRecord(new MedicalRecord().id(1)));

    public static final List<Person> personsWithBirthDate = Arrays.asList(new Person().birthday(Date.from(LocalDate.of(2010, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC))).firstName("John").lastName("Boyd").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))), new Person().birthday(Date.from(LocalDate.of(1990, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC))).firstName("Jacob").lastName("Boyd").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))));

    public static final List<Person> personsWithBirthDateNoChildren = Arrays.asList(new Person().birthday(Date.from(LocalDate.of(2000, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC))).firstName("John").lastName("Boyd").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))), new Person().birthday(Date.from(LocalDate.of(1990, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC))).firstName("Jacob").lastName("Boyd").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))));

    public static final List<String> emails = Arrays.asList("a@gmail.com", "b@gmail.com","c@gmail.com");

    public static final MedicalRecord medicalRecord= new MedicalRecord().id(1).person(new Person().id(1).firstName("John").lastName("Boyd")).allergies(Collections.singleton(new Allergy().id(1).name("allergy"))).medications(Collections.singleton(new Medication().id(1).name("medication")));

    public static final FireStation fireStation= new FireStation().id(1).address(new Address().id(1));

    public static final FireStation fireStationWithoutAddress= new FireStation().id(1);


    public static final List<Person> personsAlreadyExisting = List.of(new Person().birthday(Date.from(LocalDate.of(2010, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC))).firstName("").lastName("").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))).id(1));

    public static final Person personEmptyNames = new Person().birthday(Date.from(LocalDate.of(2010, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC))).firstName("").lastName("").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))).id(1);

    public static final Person personNonExistingNames = new Person().birthday(Date.from(LocalDate.of(2010, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC))).firstName("NonExistingFirstName").lastName("NonExistingLastName").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))).id(1);

    public static final Person person = new Person().birthday(Date.from(LocalDate.of(2010, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC))).firstName("John").lastName("Boyd").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))).id(1);

    public static final List<Person> singletonPersons = List.of(new Person().id(1).firstName("John").lastName("Boyd").address(new Address().address("address").zip(10000).city("city").fireStation(new FireStation().station(1))));

    public static final MedicalRecord medicalRecordEmptyNames= new MedicalRecord().id(1).person(new Person());


}
