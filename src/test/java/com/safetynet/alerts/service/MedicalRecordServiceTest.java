package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.impl.MedicalRecordService;
import com.safetynet.alerts.service.impl.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class MedicalRecordServiceTest {

    @TestConfiguration
    static class MedicalRecordServiceImplTestContextConfiguration {
        @Bean
        public IMedicalRecordService medicalRecordService() {
            return new MedicalRecordService();
        }
    }

    @Autowired
    private MedicalRecordService medicalRecordService;

    @MockBean
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    public void whenSaveMedicalRecord_thenMedicalRecordShouldBeSaved() {
        Mockito.when(medicalRecordRepository.save(any())).thenReturn(ConstantsTest.medicalRecord);
        MedicalRecord medicalRecord = medicalRecordService.saveMedicalRecord(new MedicalRecord());
        assertNotNull(medicalRecord);
        assertEquals(medicalRecord.getId(),1);
    }

    @Test
    public void whenFindMedicalRecordByFirstAndLastName_thenMedicalRecordShouldBeFound() {
        Mockito.when(medicalRecordRepository.findMedicalRecordByFirstAndLastName(anyString(),anyString())).thenReturn(ConstantsTest.medicalRecord);
        MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordByFirstAndLastName("firstName","lastName");
        assertNotNull(medicalRecord);
        assertEquals(medicalRecord.getId(),1);
    }

    @Test
    public void whenDeleteMedicalRecord_thenMedicalRecordShouldBeDeleted() {
        doNothing().when(medicalRecordRepository).deleteMedicalRecord(any());
        medicalRecordService.deleteMedicalRecord(ConstantsTest.medicalRecord);
        verify(medicalRecordRepository,times(1)).deleteMedicalRecord(any());
        assertDoesNotThrow(() -> medicalRecordService.deleteMedicalRecord(any()));
    }

}
