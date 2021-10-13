package com.safetynet.alerts.contoller;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.PersonInfoController;
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
@ContextConfiguration(classes = {PersonInfoController.class, PersonService.class})
@WebMvcTest
public class PersonsInfoControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PersonService personService;

    @Test
    public void givenFirstNameLastName_whenFindAllByFirstNameAndLastName_thenReturnListOfPersonInfos_Test()
            throws Exception {


        given(personService.findAllByFirstNameAndLastName(anyString(),anyString())).willReturn(ConstantsTest.personsInfos);

        mvc.perform(get("/personInfo").param("firstName","John" ).param("lastName","Boyd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName",org.hamcrest.Matchers.is("John")))
                .andExpect(jsonPath("$[0].lastName",org.hamcrest.Matchers.is("Boyd")));
    }
}
