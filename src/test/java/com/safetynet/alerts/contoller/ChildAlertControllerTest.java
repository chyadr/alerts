package com.safetynet.alerts.contoller;


import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.ChildAlertController;
import com.safetynet.alerts.dto.ChildrenAdultsInfoDTO;
import com.safetynet.alerts.service.IChildAlertService;
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
public class ChildAlertControllerTest {


    private MockMvc mvc;
    @Mock
    private IChildAlertService childAlertService;
    @InjectMocks
    private ChildAlertController childAlertController;

    @BeforeEach
    public void setup() {
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(childAlertController)
                .build();
    }


    @Test
    public void givenAddress_whenFindPersonsByAddress_thenReturnListOfChildrenAdult_Test()
            throws Exception {

        when(childAlertService.findChildrenAdults(anyString())).thenReturn(ConstantsTest.childrenAdultsInfoDTO);

        mvc.perform(get("/childAlert").param("address", "address")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adults", hasSize(1)))
                .andExpect(jsonPath("$.children", hasSize(1)));
    }

    @Test
    public void givenAddress_whenFindPersonsByAddress_thenReturnNoChildrenFound_Test()
            throws Exception {


        when(childAlertService.findChildrenAdults(anyString())).thenReturn(new ChildrenAdultsInfoDTO());

        mvc.perform(get("/childAlert").param("address", "address")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
