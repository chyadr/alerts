package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.dto.AddressPersonsMedicalRecordDTO;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.impl.FloodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(SpringExtension.class)
public class FloodServiceTest {

    @InjectMocks
    private FloodService floodService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void whenFindPersonsByAddress_thenPersonFireStationNumberDTOShouldBeFound() {
        Mockito.when(personRepository.findPersonsByStations(anyList()))
                .thenReturn(ConstantsTest.persons);
        List<AddressPersonsMedicalRecordDTO> addressPersonsMedicalRecordDTOS = floodService.findAddressPersonsMedicalRecords(any());
        assertNotNull(addressPersonsMedicalRecordDTOS);

    }


}
