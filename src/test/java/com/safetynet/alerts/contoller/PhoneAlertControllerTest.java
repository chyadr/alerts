package com.safetynet.alerts.contoller;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.PhoneAlertController;
import com.safetynet.alerts.service.IPhoneAlertService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PhoneAlertControllerTest {

    private MockMvc mvc;
    @Mock
    private IPhoneAlertService phoneAlertService;
    @InjectMocks
    private PhoneAlertController phoneAlertController;

    @BeforeEach
    public void setup() {
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(phoneAlertController)
                .build();

    }

    @Test
    public void givenFireStation_whenFindPhoneNumberByFireStation_thenReturnListOfPhoneNumber_Test()
            throws Exception {


        when(phoneAlertService.findPhoneNumberByFireStation(anyInt())).thenReturn(ConstantsTest.phones);

        mvc.perform(get("/phoneAlert").param("firestation", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$", org.hamcrest.Matchers.containsInAnyOrder("841-874-6512", "841-874-6513")));
    }


}