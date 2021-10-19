package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.service.impl.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @Test
    public void whenCreateMedicalRecord_thenMedicalRecordShouldCreated() {
        Mockito.when(medicalRecordRepository.save(any())).thenReturn(ConstantsTest.medicalRecord);
        MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(new MedicalRecord());
        assertNotNull(medicalRecord);
        assertEquals(medicalRecord.getId(), 1);
    }

    @Test
    public void whenUpdateMedicalRecord_thenMedicalRecordShouldBeUpdated() {
        Mockito.when(medicalRecordRepository.save(any())).thenReturn(ConstantsTest.medicalRecord);
        MedicalRecord medicalRecord = medicalRecordService.updateMedicalRecord(new MedicalRecord(), new MedicalRecord());
        assertNotNull(medicalRecord);
        assertEquals(medicalRecord.getId(), 1);
    }

    @Test
    public void whenFindMedicalRecordByFirstAndLastName_thenMedicalRecordShouldBeFound() {
        Mockito.when(medicalRecordRepository.findMedicalRecordByFirstAndLastName(anyString(), anyString())).thenReturn(ConstantsTest.medicalRecord);
        MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordByFirstAndLastName("firstName", "lastName");
        assertNotNull(medicalRecord);
        assertEquals(medicalRecord.getId(), 1);
    }

    @Test
    public void whenDeleteMedicalRecord_thenMedicalRecordShouldBeDeleted() {
        doNothing().when(medicalRecordRepository).deleteMedicalRecord(any());
        medicalRecordService.deleteMedicalRecord(ConstantsTest.medicalRecord);
        verify(medicalRecordRepository, times(1)).deleteMedicalRecord(any());
        assertDoesNotThrow(() -> medicalRecordService.deleteMedicalRecord(any()));
    }

}
