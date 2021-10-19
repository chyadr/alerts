package com.safetynet.alerts.service;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.dto.ChildrenAdultsInfoDTO;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.impl.ChildAlertService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class ChildAlertServiceTest {

    @InjectMocks
    private ChildAlertService childAlertService;

    @Mock
    private PersonRepository personRepository;


    @Test
    public void whenFindChildrenAdults_thenChildrenAdultsInfoDTOShouldBeFound() {
        Mockito.when(personRepository.findPersonsByAddress(anyString()))
                .thenReturn(ConstantsTest.personsWithBirthDate);
        ChildrenAdultsInfoDTO childrenAdultsInfoDTO = childAlertService.findChildrenAdults("address");
        assertNotNull(childrenAdultsInfoDTO);
        assertNotNull(childrenAdultsInfoDTO.getChildren());
        assertNotNull(childrenAdultsInfoDTO.getAdults());

        assertEquals(1, childrenAdultsInfoDTO.getChildren().size());
        assertEquals(1, childrenAdultsInfoDTO.getAdults().size());
    }
}
