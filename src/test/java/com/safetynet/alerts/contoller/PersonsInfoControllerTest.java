package com.safetynet.alerts.contoller;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.PersonInfoController;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonsInfoControllerTest {
    private MockMvc mvc;
    @Mock
    private IPersonService personService;
    @InjectMocks
    private PersonInfoController personInfoController;

    @BeforeEach
    public void setup() {
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(personInfoController)
                .build();

    }

    @Test
    public void givenFirstNameLastName_whenFindAllByFirstNameAndLastName_thenReturnListOfPersonInfos_Test()
            throws Exception {


        when(personService.findAllByFirstNameAndLastName(anyString(), anyString())).thenReturn(ConstantsTest.medicalRecordDTOS);

        mvc.perform(get("/personInfo").param("firstName", "John").param("lastName", "Boyd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].person.firstName", org.hamcrest.Matchers.is("John")))
                .andExpect(jsonPath("$[0].person.lastName", org.hamcrest.Matchers.is("Boyd")));
    }
}
