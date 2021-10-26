package com.safetynet.alerts.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.MedicalRecordController;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;
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
public class MedicalRecordControllerTest {

    private MockMvc mvc;
    @Mock
    private IMedicalRecordService medicalRecordService;
    @Mock
    private IPersonService personService;
    @InjectMocks
    private MedicalRecordController medicalRecordController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // MockMvc standalone approach
        objectMapper = new ObjectMapper();
        mvc = MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build();

    }

    @Test
    public void givenMedicalRecordWithEmptyFirstLastName_whenCreateMedicalRecord_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(post("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecordEmptyNames))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenMedicalRecordWithNonExistingFirstLastName_whenCreateMedicalRecord_thenReturnBadRequest_Test()
            throws Exception {

        when(medicalRecordService.existMedicalRecord(anyString(), anyString())).thenReturn(true);

        mvc.perform(post("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenMedicalRecord_whenCreateMedicalRecord_thenReturnCreatedMedicalRecord_Test()
            throws Exception {

        when(medicalRecordService.existMedicalRecord(anyString(), anyString())).thenReturn(false);
        when(medicalRecordService.createMedicalRecord(any())).thenReturn(ConstantsTest.medicalRecord);

        mvc.perform(post("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", org.hamcrest.Matchers.is("John")));
    }

    @Test
    public void givenMedicalRecordWithEmptyFirstLastName_whenUpdateMedicalRecord_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(put("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecordEmptyNames))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenMedicalRecordWithNonExistingFirstLastName_whenUpdateMedicalRecord_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(put("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenMedicalRecord_whenUpdateMedicalRecord_thenReturnUpdatedMedicalRecord_Test()
            throws Exception {

        when(medicalRecordService.existMedicalRecord(anyString(), anyString())).thenReturn(true);
        when(medicalRecordService.updateMedicalRecord(any())).thenReturn(ConstantsTest.medicalRecord);


        mvc.perform(put("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", org.hamcrest.Matchers.is("John")))
                .andExpect(jsonPath("$.lastName", org.hamcrest.Matchers.is("Boyd")));
    }

    @Test
    public void givenMedicalRecordWithEmptyFirstLastName_whenDeleteMedicalRecord_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(delete("/medicalRecord").param("firstName", "")
                        .param("lastName", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenPersonWithNonExistingFirstLastName_whenDeletePerson_thenReturnNoContent_Test()
            throws Exception {

        when(medicalRecordService.existMedicalRecord(anyString(), anyString())).thenReturn(false);

        mvc.perform(delete("/medicalRecord").param("firstName", "firstName")
                        .param("lastName", "lastName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenMedicalRecord_whenDeleteMedicalRecord_thenReturnDeletedMedicalRecord_Test()
            throws Exception {

        when(medicalRecordService.existMedicalRecord(anyString(), anyString())).thenReturn(true);
        doNothing().when(medicalRecordService).deleteMedicalRecord(anyString(),anyString());

        mvc.perform(delete("/medicalRecord").param("firstName", "firstName")
                        .param("lastName", "lastName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}



