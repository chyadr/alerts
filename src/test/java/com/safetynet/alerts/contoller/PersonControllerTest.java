package com.safetynet.alerts.contoller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.FireController;
import com.safetynet.alerts.conroller.PersonController;
import com.safetynet.alerts.model.Person;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {PersonController.class, PersonService.class})
@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;


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

        given(personService.findAllByFirstNameAndLastName(anyString(),anyString())).willReturn(ConstantsTest.personsAlreadyExisting);

        mvc.perform(post("/person").content(objectMapper.writeValueAsString(ConstantsTest.person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }



    @Test
    public void givenPerson_whenCreatePerson_thenReturnCreatedPerson_Test()
            throws Exception {

        given(personService.findAllByFirstNameAndLastName(anyString(),anyString())).willReturn(Collections.emptyList());
        given(personService.savePerson(any())).willReturn(ConstantsTest.person);

        mvc.perform(post("/person").content(objectMapper.writeValueAsString(ConstantsTest.person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",org.hamcrest.Matchers.is(1)));
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

        given(personService.findAllByFirstNameAndLastName(anyString(),anyString())).willReturn(Collections.emptyList());

        mvc.perform(put("/person").content(objectMapper.writeValueAsString(ConstantsTest.personNonExistingNames))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    public void givenPersonNotUnique_whenUpdatePerson_thenReturnBadRequest_Test()
            throws Exception {

        given(personService.findAllByFirstNameAndLastName(anyString(),anyString())).willReturn(ConstantsTest.persons);

        mvc.perform(put("/person").content(objectMapper.writeValueAsString(ConstantsTest.person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenPerson_whenUpdatePerson_thenReturnUpdatedPerson_Test()
            throws Exception {

        given(personService.findAllByFirstNameAndLastName(anyString(), anyString())).willReturn(ConstantsTest.singletonPersons);
        given(personService.savePerson(any())).willReturn(ConstantsTest.person);

        mvc.perform(put("/person").content(objectMapper.writeValueAsString(ConstantsTest.person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id", org.hamcrest.Matchers.is(1)));
    }


    @Test
    public void givenPersonWithEmptyFirstLastName_whenDeletePerson_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(delete("/person").param("firstName","")
                        .param("lastName","")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenPersonWithNonExistingFirstLastName_whenDeletePerson_thenReturnNoContent_Test()
            throws Exception {

        given(personService.findAllByFirstNameAndLastName(anyString(),anyString())).willReturn(Collections.emptyList());

        mvc.perform(delete("/person").param("firstName","firstName")
                        .param("lastName","lastName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenPerson_whenDeletePerson_thenReturnDeletedPerson_Test()
            throws Exception {

        given(personService.findAllByFirstNameAndLastName(anyString(), anyString())).willReturn(ConstantsTest.singletonPersons);
        doNothing().when(personService).deletePersons(any());

        mvc.perform(delete("/person").param("firstName","firstName")
                        .param("lastName","lastName")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

}
