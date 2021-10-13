package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.service.impl.FireStationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FireStationServiceTest {

    @TestConfiguration
    static class FireStationServiceImplTestContextConfiguration {
        @Bean
        public IFireStationService fireStationService() {
            return new FireStationService();
        }
    }

    @Autowired
    private FireStationService fireStationService;

    @MockBean
    private FireStationRepository fireStationRepository;

    @Test
    public void whenCreateFireStation_thenFireStationShouldBeCreated() {
        Mockito.when(fireStationRepository.save(any())).thenReturn(ConstantsTest.fireStation);
        FireStation fireStation = fireStationService.createFireStation(new FireStation());
        assertNotNull(fireStation);
        assertEquals(fireStation.getId(),1);
    }

    @Test
    public void whenSaveFireStation_thenFireStationShouldBeSaved() {
        Mockito.when(fireStationRepository.save(any())).thenReturn(ConstantsTest.fireStation);
        FireStation fireStation = fireStationService.saveFireStation(new FireStation());
        assertNotNull(fireStation);
        assertEquals(fireStation.getId(),1);
    }

    @Test
    public void whenFindFireStationById_thenFireStationShouldBeFound() {
        Mockito.when(fireStationRepository.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.fireStation));
        Optional<FireStation> fireStation = fireStationService.findFireStationById(1);
        assertNotNull(fireStation);
        assertTrue(fireStation.isPresent());
        assertEquals(fireStation.get().getId(),1);
    }

    @Test
    public void whenDeleteFireStation_thenFireStationShouldBeDeleted() {
        doNothing().when(fireStationRepository).delete(any());
        fireStationService.deleteFireStation(ConstantsTest.fireStation);
        verify(fireStationRepository,times(1)).delete(any());
        assertDoesNotThrow(() -> fireStationService.deleteFireStation(any()));
    }


}
