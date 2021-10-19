package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.repository.PersonRepository;
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
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(SpringExtension.class)

public class PhoneAlertServiceTest {

    @InjectMocks
    private PhoneAlertService phoneAlertService;

    @Mock
    private PersonRepository personRepository;


    @Test
    public void whenFindPhoneNumberByFireStation_thenListOfPhonesShouldBeFound() {
        Mockito.when(personRepository.findPhoneNumberByFireStation(anyInt()))
                .thenReturn(ConstantsTest.phones);
        List<String> phones = phoneAlertService.findPhoneNumberByFireStation(1);
        assertNotNull(phones);
        assertEquals(2, phones.size());
    }

}
