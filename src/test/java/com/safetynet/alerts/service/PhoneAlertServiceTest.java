package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.service.impl.PhoneAlertService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)

public class PhoneAlertServiceTest {

    @InjectMocks
    private PhoneAlertService phoneAlertService;

    @Mock
    private Data data;

    @Test
    public void whenFindPhoneNumberByFireStation_thenListOfPhonesShouldBeFound() {
        Mockito.when(data.getPersons()).thenReturn(ConstantsTest.persons);
        Mockito.when(data.getFirestations()).thenReturn(ConstantsTest.fireStations);
        List<String> phones = phoneAlertService.findPhoneNumberByFireStationNumber(1);
        assertNotNull(phones);
        assertEquals(2, phones.size());
    }

}
