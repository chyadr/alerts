package com.safetynet.alerts;

import com.safetynet.alerts.dto.*;
import com.safetynet.alerts.model.*;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

public final class ConstantsTest {

    public static final List<String> phones = Arrays.asList("841-874-6512", "841-874-6513");

    public static final List<Person> persons = new ArrayList<>(Arrays.asList(new Person().email("email").firstName("John").lastName("Boyd").address("address").zip(10000).city("city"),
            new Person().email("email").firstName("Jacob").lastName("Boyd").address("address").zip(10000).city("city"),
            new Person().email("email").firstName("xxx").lastName("yyy").address("zzz").zip(10000).city("rrr"),
            new Person().firstName("delete").lastName("delete").city("delete").address("delete")));

    public static final List<PersonMedicalRecordDTO> medicalRecordDTOS = Arrays.asList(new PersonMedicalRecordDTO().medicalRecord(new com.safetynet.alerts.model.MedicalRecord()).person(new com.safetynet.alerts.model.Person().firstName("John").lastName("Boyd")));

    public static final List<MedicalRecord> medicalRecords = new ArrayList<>(Arrays.asList(new MedicalRecord().firstName("John").lastName("Boyd").allergies(Collections.singleton("allergy")).medications(Collections.singleton("medication")).birthdate(Date.from(LocalDate.of(2010, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC))),
            new MedicalRecord().firstName("Jacob").lastName("Boyd").allergies(Collections.singleton("allergy")).medications(Collections.singleton("medication")).birthdate(Date.from(LocalDate.of(2000, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC)))
            ,new MedicalRecord().firstName("deleteMedical").lastName("deleteMedical"),new MedicalRecord().firstName("updateMedical").lastName("updateMedical").medications(new HashSet<>(List.of("med1","med2")))));

    public static final List<String> emails = Arrays.asList("a@gmail.com", "b@gmail.com", "c@gmail.com");

    public static final MedicalRecord medicalRecord = new MedicalRecord().firstName("John").lastName("Boyd").allergies(Collections.singleton("allergy")).medications(Collections.singleton("medication"));

    public static final FireStation fireStation = new FireStation().station(1).address("address");

    public static final FireStation fireStationWithoutAddress = new FireStation();

    public static final FireStation fireStationWithAddressStationNull = new FireStation().address("address").station(null);

    public static final List<FireStation> fireStations = new ArrayList<>(Arrays.asList(new FireStation().station(1).address("address"),new FireStation().station(2).address("address"),new FireStation().station(3).address("address3"),new FireStation().station(2).address("addressToBeDeleted")));

    public static final Person person = new Person().firstName("John").lastName("Boyd").address("address").zip(10000).city("city");

    public static final Person personEmptyNames = new Person().address("address").zip(10000).city("city");


    public static final MedicalRecord medicalRecordEmptyNames = new MedicalRecord();

    public static final ChildrenAdultsInfoDTO childrenAdultsInfoDTO = new ChildrenAdultsInfoDTO().children(Collections.singletonList(new com.safetynet.alerts.model.Person())).adults(Collections.singletonList(new com.safetynet.alerts.model.Person()));

    public static final Map<String,List<PersonMedicalRecordDTO>> mapPersonMedicalRecordDTO = Collections.singletonMap("address",List.of(new PersonMedicalRecordDTO().person(new com.safetynet.alerts.model.Person().firstName("Boyd"))));

    public static final FireDTO personFireStationDTO = new FireDTO();

    public static final PersonInfosDTO personInfosDTO = new PersonInfosDTO().numberOfAdult(1).numberOfChild(1).persons(List.of(new com.safetynet.alerts.model.Person(), new com.safetynet.alerts.model.Person()));




}
