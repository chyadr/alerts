package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.dto.PersonFireStationNumberDTO;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.impl.FireService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class FireServiceTest {

    @InjectMocks
    private FireService fireService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void whenFindPersonsByAddress_thenPersonFireStationNumberDTOShouldBeFound() {
        Mockito.when(personRepository.findPersonsByAddress(anyString()))
                .thenReturn(ConstantsTest.personsWithBirthDate);
        PersonFireStationNumberDTO personFireStationNumberDTO = fireService.findPersonFireStationNumber("address");
        assertNotNull(personFireStationNumberDTO);
        assertNotNull(personFireStationNumberDTO.getStationNumber());
    }

}
