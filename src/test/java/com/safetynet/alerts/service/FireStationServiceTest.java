package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.dto.PersonInfosDTO;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.impl.FireStationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FireStationServiceTest {

    @InjectMocks
    private FireStationService fireStationService;

    @Mock
    private FireStationRepository fireStationRepository;
    @Mock
    private PersonRepository personRepository;


    @Test
    public void whenCreateFireStation_thenFireStationShouldBeCreated() {
        Mockito.when(fireStationRepository.save(any())).thenReturn(ConstantsTest.fireStation);
        FireStation fireStation = fireStationService.createFireStation(new FireStation());
        assertNotNull(fireStation);
        assertEquals(fireStation.getId(), 1);
    }

    @Test
    public void whenSaveFireStation_thenFireStationShouldBeSaved() {
        Mockito.when(fireStationRepository.save(any())).thenReturn(ConstantsTest.fireStation);
        FireStation fireStation = fireStationService.saveFireStation(new FireStation());
        assertNotNull(fireStation);
        assertEquals(fireStation.getId(), 1);
    }

    @Test
    public void whenFindFireStationById_thenFireStationShouldBeFound() {
        Mockito.when(fireStationRepository.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.fireStation));
        Optional<FireStation> fireStation = fireStationService.findFireStationById(1);
        assertNotNull(fireStation);
        assertTrue(fireStation.isPresent());
        assertEquals(fireStation.get().getId(), 1);
    }

    @Test
    public void whenDeleteFireStation_thenFireStationShouldBeDeleted() {
        doNothing().when(fireStationRepository).delete(any());
        fireStationService.deleteFireStation(ConstantsTest.fireStation);
        verify(fireStationRepository, times(1)).delete(any());
        assertDoesNotThrow(() -> fireStationService.deleteFireStation(any()));
    }

    @Test
    public void whenFindPersonsByStationNumber_thenPersonShouldBeFound() {
        when(personRepository.findPersonsByStationNumber(anyInt())).thenReturn(ConstantsTest.personsWithBirthDate);
        PersonInfosDTO personInfosDTO = fireStationService.findPersonsByStationNumber(1);
        assertNotNull(personInfosDTO);
        assertTrue(personInfosDTO.getNumberOfAdult() > 0);
        assertTrue(personInfosDTO.getNumberOfChild() > 0);

    }


}
