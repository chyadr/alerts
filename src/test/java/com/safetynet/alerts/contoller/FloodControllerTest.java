package com.safetynet.alerts.contoller;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.FloodController;
import com.safetynet.alerts.service.IFloodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FloodControllerTest {


    private MockMvc mvc;
    @Mock
    private IFloodService floodService;
    @InjectMocks
    private FloodController floodController;

    @BeforeEach
    public void setup() {
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(floodController)
                .build();

    }


    @Test
    public void givenListOfStations_whenFindAddressPersonsMedicalRecordsByStations_thenReturnListOfPersons_Test()
            throws Exception {


        when(floodService.findAddressPersonsMedicalRecords(any())).thenReturn(ConstantsTest.mapPersonMedicalRecordDTO);

        mvc.perform(get("/flood/stations").param("stations", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
