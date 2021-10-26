package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.model.Data;
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

@ExtendWith(SpringExtension.class)
public class CommunityEmailServiceTest {

    @InjectMocks
    private CommunityEmailService communityEmailService;

    @Mock
    private Data data;


    @Test
    public void whenFindAllAddressMailsByCity_thenListOfPhonesShouldBeFound() {
        Mockito.when(data.getPersons()).thenReturn(ConstantsTest.persons);
        List<String> emails = communityEmailService.findAllAddressMailsByCity("city");
        assertNotNull(emails);
        assertEquals(2, emails.size());
    }
}
