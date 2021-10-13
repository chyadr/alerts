package com.safetynet.alerts.contoller;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.CommunityEmailController;
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
@ContextConfiguration(classes = {CommunityEmailController.class, PersonService.class})
@WebMvcTest
public class CommunityEmailControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PersonService personService;


    @Test
    public void givenCity_whenFindPersonsByStationNumber_thenReturnListOfEmail_Test()
            throws Exception {


        given(personService.findAllAddressMailsByCity(anyString())).willReturn(ConstantsTest.emails);

        mvc.perform(get("/communityEmail").param("city","city")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$",org.hamcrest.Matchers.containsInAnyOrder("a@gmail.com", "b@gmail.com","c@gmail.com")));
    }
}
