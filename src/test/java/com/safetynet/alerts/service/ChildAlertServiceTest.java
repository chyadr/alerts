package com.safetynet.alerts.service;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.dto.ChildrenAdultsInfoDTO;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.service.impl.ChildAlertService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class ChildAlertServiceTest {

    @InjectMocks
    private ChildAlertService childAlertService;

    @Mock
    private Data data;


    @Test
    public void whenFindChildrenAdults_thenChildrenAdultsInfoDTOShouldBeFound() {
        Mockito.when(data.getPersons()).thenReturn(ConstantsTest.persons);
        Mockito.when(data.getMedicalrecords()).thenReturn(ConstantsTest.medicalRecords);

        ChildrenAdultsInfoDTO childrenAdultsInfoDTO = childAlertService.findChildrenAdults("address");
        assertNotNull(childrenAdultsInfoDTO);
        assertNotNull(childrenAdultsInfoDTO.getChildren());
        assertNotNull(childrenAdultsInfoDTO.getAdults());

        assertEquals(1, childrenAdultsInfoDTO.getChildren().size());
        assertEquals(1, childrenAdultsInfoDTO.getAdults().size());
    }
}
