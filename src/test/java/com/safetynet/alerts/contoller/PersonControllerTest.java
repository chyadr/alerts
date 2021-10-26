package com.safetynet.alerts.contoller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.PersonController;
import com.safetynet.alerts.service.IPersonService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    private MockMvc mvc;
    @Mock
    private IPersonService personService;
    @InjectMocks
    private PersonController personController;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // MockMvc standalone approach
        objectMapper = new ObjectMapper();
        mvc = MockMvcBuilders.standaloneSetup(personController)
                .build();

    }


    @Test
    public void givenPersonWithEmptyFirstLastName_whenCreatePerson_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(post("/person").content(objectMapper.writeValueAsString(ConstantsTest.personEmptyNames))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenPersonWithNonExistingFirstLastName_whenCreatePerson_thenReturnBadRequest_Test()
            throws Exception {

        when(personService.existPersonByFirstNameAndLastName(anyString(), anyString())).thenReturn(true);

        mvc.perform(post("/person").content(objectMapper.writeValueAsString(ConstantsTest.person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void givenPerson_whenCreatePerson_thenReturnCreatedPerson_Test()
            throws Exception {

        when(personService.existPersonByFirstNameAndLastName(anyString(), anyString())).thenReturn(false);
        when(personService.createPerson(any())).thenReturn(ConstantsTest.person);

        mvc.perform(post("/person").content(objectMapper.writeValueAsString(ConstantsTest.person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", org.hamcrest.Matchers.is("John")))
                .andExpect(jsonPath("$.lastName", org.hamcrest.Matchers.is("Boyd")));
    }


    @Test
    public void givenPersonWithEmptyFirstLastName_whenUpdatePerson_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(put("/person").content(objectMapper.writeValueAsString(ConstantsTest.personEmptyNames))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenPersonWithNonExistingFirstLastName_whenUpdatePerson_thenReturnNoContent_Test()
            throws Exception {

        when(personService.existPersonByFirstNameAndLastName(anyString(), anyString())).thenReturn(false);

        mvc.perform(put("/person").content(objectMapper.writeValueAsString(ConstantsTest.person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void givenPerson_whenUpdatePerson_thenReturnUpdatedPerson_Test()
            throws Exception {

        when(personService.existPersonByFirstNameAndLastName(anyString(), anyString())).thenReturn(true);
        when(personService.updatePerson(any())).thenReturn(ConstantsTest.person);

        mvc.perform(put("/person").content(objectMapper.writeValueAsString(ConstantsTest.person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName", org.hamcrest.Matchers.is("John")))
                .andExpect(jsonPath("$.lastName", org.hamcrest.Matchers.is("Boyd")));
    }


    @Test
    public void givenPersonWithEmptyFirstLastName_whenDeletePerson_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(delete("/person").param("firstName", "")
                        .param("lastName", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenPersonWithNonExistingFirstLastName_whenDeletePerson_thenReturnNoContent_Test()
            throws Exception {

        when(personService.existPersonByFirstNameAndLastName(anyString(), anyString())).thenReturn(false);

        mvc.perform(delete("/person").param("firstName", "firstName")
                        .param("lastName", "lastName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenPerson_whenDeletePerson_thenReturnDeletedPerson_Test()
            throws Exception {

        when(personService.existPersonByFirstNameAndLastName(anyString(), anyString())).thenReturn(true);
        doNothing().when(personService).deletePerson(anyString(),anyString());

        mvc.perform(delete("/person").param("firstName", "firstName")
                        .param("lastName", "lastName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

}
