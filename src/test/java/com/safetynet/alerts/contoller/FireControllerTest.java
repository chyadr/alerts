package com.safetynet.alerts.contoller;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.FireController;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {FireController.class, PersonService.class})
@WebMvcTest
public class FireControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PersonService personService;


    @Test
    public void givenAddress_whenFindPersonsByStationNumber_thenReturnListOfPersons_Test()
            throws Exception {


        given(personService.findPersonsByAddress(anyString())).willReturn(ConstantsTest.persons);

        mvc.perform(get("/fire").param("address", "address" )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.personDTOS", hasSize(2)))
                .andExpect(jsonPath("$.stationNumber",org.hamcrest.Matchers.is(1)))
                .andExpect(jsonPath("$.personDTOS[0].addressDTO.address",org.hamcrest.Matchers.is("address")));
    }
}
