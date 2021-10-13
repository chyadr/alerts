package com.safetynet.alerts.contoller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.FireStationController;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.impl.FireStationService;
import com.safetynet.alerts.service.impl.PersonService;
import org.apache.tomcat.util.bcel.Const;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {FireStationController.class, PersonService.class})
@WebMvcTest
public class FireStationControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PersonService personService;

    @MockBean
    private FireStationService fireStationService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void givenStationNumber_whenFindPersonsByStationNumber_thenReturnListOfPersons_Test()
            throws Exception {


        given(personService.findPersonsByStationNumber(anyInt())).willReturn(ConstantsTest.personsWithBirthDate);

        mvc.perform(get("/firestation").param("stationNumber", "1" )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.personDTOS", hasSize(2)))
                .andExpect(jsonPath("$.numberOfAdult",org.hamcrest.Matchers.is(1)))
                .andExpect(jsonPath("$.numberOfChild",org.hamcrest.Matchers.is(1)));
    }

    @Test
    public void givenFireStationWithoutAddress_whenCreateFireStation_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(post("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStationWithoutAddress))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenFireStationMappedExistingAddress_whenCreateFireStation_thenReturnFireStation_Test()
            throws Exception {

        given(fireStationService.findFireStationsByAddressId(anyInt())).willReturn(Collections.singletonList(new FireStation()));

        mvc.perform(post("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStation))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenFireStation_whenCreateFireStation_thenReturnFireStation_Test()
            throws Exception {

        given(fireStationService.createFireStation(any())).willReturn(ConstantsTest.fireStation);

        mvc.perform(post("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStation))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",org.hamcrest.Matchers.is(1)));
    }

    @Test
    public void givenFireStation_whenUpdateFireStationNumber_thenReturnBadRequest_Test()
            throws Exception {

        given(fireStationService.findFireStationById(anyInt())).willReturn(Optional.empty());

        mvc.perform(put("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStation))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenFireStation_whenUpdateFireStationNumber_thenReturnUpdatedFireStation_Test()
            throws Exception {

        given(fireStationService.findFireStationById(anyInt())).willReturn(Optional.of(ConstantsTest.fireStation));
        given(fireStationService.saveFireStation(any())).willReturn(ConstantsTest.fireStation);

        mvc.perform(put("/firestation").content(objectMapper.writeValueAsString(ConstantsTest.fireStation))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id",org.hamcrest.Matchers.is(1)));
    }

    @Test
    public void givenFireStationId_whenDeleteFireStation_thenReturnBadRequest_Test()
            throws Exception {

        given(fireStationService.findFireStationById(anyInt())).willReturn(Optional.empty());

        mvc.perform(delete("/firestation").param("id","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenFireStationId_whenDeleteFireStation_thenReturnDeletedFireStation_Test()
            throws Exception {

        given(fireStationService.findFireStationById(anyInt())).willReturn(Optional.of(ConstantsTest.fireStation));
        given(fireStationService.saveFireStation(any())).willReturn(ConstantsTest.fireStation);

        mvc.perform(delete("/firestation").param("id","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
