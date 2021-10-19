package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.impl.CommunityEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class CommunityEmailServiceTest {

    @InjectMocks
    private CommunityEmailService communityEmailService;

    @Mock
    private PersonRepository personRepository;


    @Test
    public void whenFindAllAddressMailsByCity_thenListOfPhonesShouldBeFound() {
        Mockito.when(personRepository.findAllAddressMailsByCity(anyString()))
                .thenReturn(ConstantsTest.emails);
        List<String> emails = communityEmailService.findAllAddressMailsByCity("city");
        assertNotNull(emails);
        assertEquals(3, emails.size());
    }
}
