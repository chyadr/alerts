package com.safetynet.alerts.contoller;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.FireController;
import com.safetynet.alerts.service.IFireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FireControllerTest {


    private MockMvc mvc;
    @Mock
    private IFireService fireService;
    @InjectMocks
    private FireController fireController;

    @BeforeEach
    public void setup() {
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(fireController)
                .build();
    }

    @Test
    public void givenAddress_whenFindPersonsByStationNumber_thenReturnListOfPersons_Test()
            throws Exception {


        when(fireService.findPersonFireStationNumber(anyString())).thenReturn(ConstantsTest.personFireStationDTO);

        mvc.perform(get("/fire").param("address", "address")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
