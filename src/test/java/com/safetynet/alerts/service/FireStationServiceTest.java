package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.dto.PersonInfosDTO;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.impl.FireStationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class FireStationServiceTest {

    @InjectMocks
    private FireStationService fireStationService;

    @Mock
    private Data data;

    @Test
    public void whenCreateFireStation_thenFireStationShouldBeCreated() {
        FireStation fireStation = fireStationService.createFireStation(new FireStation());
        assertNotNull(fireStation);
    }

    @Test
    public void whenSaveFireStation_thenFireStationShouldBeSaved() {
        Mockito.when(data.getFirestations()).thenReturn(ConstantsTest.fireStations);
        FireStation fireStation = fireStationService.updateFireStation(new FireStation().address("address3").station(100));
        assertNotNull(fireStation);
        assertEquals(fireStation.getAddress(),"address3");
        assertEquals(fireStation.getStation(),100);
    }


    @Test
    public void whenExistFireStationsByAddress_thenTrueShouldBeExisted() {
        Mockito.when(data.getFirestations()).thenReturn(ConstantsTest.fireStations);
        boolean existFireStation= fireStationService.existFireStationsByAddress("address3");
        assertTrue(existFireStation);
    }



    @Test
    public void whenDeleteFireStation_thenFireStationShouldBeDeleted() {
        Mockito.when(data.getFirestations()).thenReturn(ConstantsTest.fireStations);

        assertDoesNotThrow(() -> fireStationService.deleteFireStation("addressToBeDeleted"));
    }
    @Test
    public void WhenFindPersonsByStationNumber_thenPersonShouldBeFound() {
        Mockito.when(data.getPersons()).thenReturn(ConstantsTest.persons);
        Mockito.when(data.getFirestations()).thenReturn(ConstantsTest.fireStations);
        Mockito.when(data.getMedicalrecords()).thenReturn(ConstantsTest.medicalRecords);
        PersonInfosDTO personInfosDTO = fireStationService.findPersonsByStationNumber(1);

        assertNotNull(personInfosDTO);
        assertEquals(1, personInfosDTO.getNumberOfAdult());
        assertEquals(1,personInfosDTO.getNumberOfChild());
        assertEquals(2,personInfosDTO.getPersons().size());

    }

}

