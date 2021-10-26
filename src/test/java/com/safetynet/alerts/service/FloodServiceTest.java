package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.dto.PersonMedicalRecordDTO;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.service.impl.FloodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class FloodServiceTest {

    @InjectMocks
    private FloodService floodService;


    @Mock
    private Data data;


    @Test
    public void whenFindPersonsByAddress_thenPersonFireStationNumberDTOShouldBeFound() {
        Mockito.when(data.getFirestations()).thenReturn(ConstantsTest.fireStations);
        Mockito.when(data.getPersons()).thenReturn(ConstantsTest.persons);
        Mockito.when(data.getMedicalrecords()).thenReturn(ConstantsTest.medicalRecords);

        Map<String, List<PersonMedicalRecordDTO>> map = floodService.findAddressPersonsMedicalRecords(List.of(1,2,3));
        assertNotNull(map);

    }


}
