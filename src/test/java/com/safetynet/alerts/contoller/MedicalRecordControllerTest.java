package com.safetynet.alerts.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.conroller.MedicalRecordController;
import com.safetynet.alerts.conroller.PersonController;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.impl.MedicalRecordService;
import com.safetynet.alerts.service.impl.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {MedicalRecordController.class, MedicalRecordService.class})
@WebMvcTest
public class MedicalRecordControllerTest {


    @Autowired
    private MockMvc mvc;
    @MockBean
    private MedicalRecordService medicalRecordService;
    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;


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

        given(personService.findAllByFirstNameAndLastName(anyString(),anyString())).willReturn(Collections.emptyList());

        mvc.perform(post("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void givenFirstLastNameAreNotUnique_whenCreateMedicalRecord_thenReturnBadRequest_Test()
            throws Exception {

        given(personService.findAllByFirstNameAndLastName(anyString(),anyString())).willReturn(ConstantsTest.persons);

        mvc.perform(post("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void givenMedicalRecord_whenCreateMedicalRecord_thenReturnCreatedMedicalRecord_Test()
            throws Exception {

        given(personService.findAllByFirstNameAndLastName(anyString(),anyString())).willReturn(ConstantsTest.personsAlreadyExisting);
        given(medicalRecordService.saveMedicalRecord(any())).willReturn(ConstantsTest.medicalRecord);

        mvc.perform(post("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",org.hamcrest.Matchers.is(1)))
                .andExpect(jsonPath("$.person.id",org.hamcrest.Matchers.is(1)));
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

        given(medicalRecordService.findMedicalRecordByFirstAndLastName(anyString(),anyString())).willReturn(null);

        mvc.perform(put("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenMedicalRecord_whenUpdateMedicalRecord_thenReturnUpdatedMedicalRecord_Test()
            throws Exception {

        given(medicalRecordService.findMedicalRecordByFirstAndLastName(anyString(),anyString())).willReturn(new MedicalRecord());
        given(medicalRecordService.saveMedicalRecord(any())).willReturn(ConstantsTest.medicalRecord);

        mvc.perform(put("/medicalRecord").content(objectMapper.writeValueAsString(ConstantsTest.medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",org.hamcrest.Matchers.is(1)))
                .andExpect(jsonPath("$.person.id",org.hamcrest.Matchers.is(1)));
    }

    @Test
    public void givenMedicalRecordWithEmptyFirstLastName_whenDeleteMedicalRecord_thenReturnBadRequest_Test()
            throws Exception {

        mvc.perform(delete("/medicalRecord").param("firstName","")
                        .param("lastName","")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void givenPersonWithNonExistingFirstLastName_whenDeletePerson_thenReturnNoContent_Test()
            throws Exception {

        given(medicalRecordService.findMedicalRecordByFirstAndLastName(anyString(),anyString())).willReturn(null);

        mvc.perform(delete("/medicalRecord").param("firstName","firstName")
                        .param("lastName","lastName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenMedicalRecord_whenDeleteMedicalRecord_thenReturnDeletedMedicalRecord_Test()
            throws Exception {

        given(medicalRecordService.findMedicalRecordByFirstAndLastName(anyString(), anyString())).willReturn(ConstantsTest.medicalRecord);
        doNothing().when(medicalRecordService).deleteMedicalRecord(any());

        mvc.perform(delete("/medicalRecord").param("firstName","firstName")
                        .param("lastName","lastName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}



