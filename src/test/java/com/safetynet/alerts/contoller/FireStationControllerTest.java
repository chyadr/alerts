package com.safetynet.alerts.contoller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.FireStationController;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.service.impl.FireStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FireStationControllerTest {

    private MockMvc mvc;
    @Mock
    private FireStationService fireStationService;
    private ObjectMapper objectMapper;

    @InjectMocks
    private FireStationController fireStationController;

    @BeforeEach
    public void setup() {
        // MockMvc standalone approach
        objectMapper = new ObjectMapper();
        mvc = MockMvcBuilders.standaloneSetup(fireStationController)
                .build();

    }

    @Test
    public void givenStationNumber_whenFindPersonsByStationNumber_thenReturnListOfPersons_Test()
            throws Exception {


        when(fireStationService.findPersonsByStationNumber(anyInt())).thenReturn(ConstantsTest.personInfosDTO);

        mvc.perform(get("/firestation").param("stationNumber", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons", hasSize(2)))
                .andExpect(jsonPath("$.numberOfAdult", org.hamcrest.Matchers.is(1)))
                .andExpect(jsonPath("$.numberOfChild", org.hamcrest.Matchers.is(1)));
    }

    @Test
    public void givenFireStationWithoutAddress_whenCreateFireStation_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(post("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStationWithoutAddress))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenFireStationWithAddressStationNull_whenCreateFireStation_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(post("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStationWithAddressStationNull))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenFireStationMappedExistingAddress_whenCreateFireStation_thenReturnBadRequest_Test()
            throws Exception {

        when(fireStationService.existFireStationsByAddress(anyString())).thenReturn(true);
        

        mvc.perform(post("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStation))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenFireStation_whenCreateFireStation_thenReturnFireStation_Test()
            throws Exception {

        when(fireStationService.existFireStationsByAddress(anyString())).thenReturn(false);
        when(fireStationService.createFireStation(any())).thenReturn(ConstantsTest.fireStation);

        mvc.perform(post("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStation))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.station", org.hamcrest.Matchers.is(1)));
    }

    @Test
    public void givenFireStation_whenUpdateFireStationNumber_thenReturnBadRequest_Test()
            throws Exception {


        mvc.perform(put("/firestation").content(objectMapper.writeValueAsString(new FireStation()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenFireStation_whenUpdateNonExistingFireStationNumber_thenReturnBadRequest_Test()
            throws Exception {

        when(fireStationService.existFireStationsByAddress(anyString())).thenReturn(false);


        mvc.perform(put("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStation))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenFireStation_whenUpdateFireStationNumber_thenReturnUpdatedFireStation_Test()
            throws Exception {

        when(fireStationService.existFireStationsByAddress(anyString())).thenReturn(true);
        when(fireStationService.updateFireStation(any())).thenReturn(ConstantsTest.fireStation);


        mvc.perform(put("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStation))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.station", org.hamcrest.Matchers.is(1)));
    }

    @Test
    public void givenFireStationId_whenDeleteFireStation_thenReturnBadRequest_Test()
            throws Exception {
        when(fireStationService.existFireStationsByAddress(anyString())).thenReturn(false);

        mvc.perform(delete("/firestation").param("address", "address")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenFireStationId_whenDeleteFireStation_thenReturnDeletedFireStation_Test()
            throws Exception {

        when(fireStationService.existFireStationsByAddress(anyString())).thenReturn(true);
        mvc.perform(delete("/firestation").param("address", "address")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
