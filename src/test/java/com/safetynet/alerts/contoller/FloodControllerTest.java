package com.safetynet.alerts.contoller;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.FloodController;
import com.safetynet.alerts.service.impl.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {FloodController.class, PersonService.class})
@WebMvcTest
public class FloodControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PersonService personService;


    @Test
    public void givenListOfStations_whenFindAddressPersonsMedicalRecordsByStations_thenReturnListOfPersons_Test()
            throws Exception {


        given(personService.findPersonsByStations(any())).willReturn(ConstantsTest.persons);

        mvc.perform(get("/flood/stations").param("stations", "1" )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].personDTOS[0].lastName",org.hamcrest.Matchers.is("Boyd")))
                .andExpect(jsonPath("$[0].addressDTO.address",org.hamcrest.Matchers.is("address")))
                .andExpect(jsonPath("$[0].personDTOS[1].lastName",org.hamcrest.Matchers.is("Boyd")));
    }

}
