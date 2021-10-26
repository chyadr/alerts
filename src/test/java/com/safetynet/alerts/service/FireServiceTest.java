package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.dto.FireDTO;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.service.impl.FireService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class FireServiceTest {

    @InjectMocks
    private FireService fireService;

    @Mock
    private Data data;

    @Test
    public void whenFindPersonsByAddress_thenPersonFireStationNumberDTOShouldBeFound() {
        Mockito.when(data.getFirestations()).thenReturn(ConstantsTest.fireStations);
        Mockito.when(data.getPersons()).thenReturn(ConstantsTest.persons);
        Mockito.when(data.getMedicalrecords()).thenReturn(ConstantsTest.medicalRecords);

        FireDTO personFireStationNumberDTO = fireService.findPersonFireStationNumber("address");
        assertNotNull(personFireStationNumberDTO);
    }

}
