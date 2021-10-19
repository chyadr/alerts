package com.safetynet.alerts.contoller;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.CommunityEmailController;
import com.safetynet.alerts.service.ICommunityEmailService;
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
public class CommunityEmailControllerTest {

    private MockMvc mvc;
    @Mock
    private ICommunityEmailService communityEmailService;
    @InjectMocks
    private CommunityEmailController communityEmailController;

    @BeforeEach
    public void setup() {
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(communityEmailController)
                .build();
    }

    @Test
    public void givenCity_whenFindPersonsByStationNumber_thenReturnListOfEmail_Test()
            throws Exception {


        when(communityEmailService.findAllAddressMailsByCity(anyString())).thenReturn(ConstantsTest.emails);

        mvc.perform(get("/communityEmail").param("city", "city")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$", org.hamcrest.Matchers.containsInAnyOrder("a@gmail.com", "b@gmail.com", "c@gmail.com")));
    }
}
