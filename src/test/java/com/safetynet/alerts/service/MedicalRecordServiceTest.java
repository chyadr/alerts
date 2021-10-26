package com.safetynet.alerts.service;

import com.safetynet.alerts.ConstantsTest;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.impl.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class MedicalRecordServiceTest {


    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @Mock
    private Data data;

    @Test
    public void whenCreateMedicalRecord_thenMedicalRecordShouldCreated() {
        MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(new MedicalRecord());
        assertNotNull(medicalRecord);
    }

    @Test
    public void whenUpdateMedicalRecord_thenMedicalRecordShouldBeUpdated() {
        Mockito.when(data.getMedicalrecords()).thenReturn(ConstantsTest.medicalRecords);
        MedicalRecord medicalRecord = medicalRecordService.updateMedicalRecord(new MedicalRecord().firstName("updateMedical").lastName("updateMedical").medications(Set.of("med3","med4")));
        assertNotNull(medicalRecord);
        assertEquals(medicalRecord.getMedications().size(), 2);
        assertTrue(medicalRecord.getMedications().stream().anyMatch(m -> m.equals("med3") || m.equals("med4")));
    }


    @Test
    public void whenDeleteMedicalRecord_thenMedicalRecordShouldBeDeleted() {
        Mockito.when(data.getMedicalrecords()).thenReturn(ConstantsTest.medicalRecords);
        assertDoesNotThrow(() -> medicalRecordService.deleteMedicalRecord("deleteMedical","deleteMedical"));
    }


    @Test
    public void whenExistMedicalRecord_thenTrueShouldBeExisted() {
        Mockito.when(data.getMedicalrecords()).thenReturn(ConstantsTest.medicalRecords);
        boolean existMedicalRecord= medicalRecordService.existMedicalRecord("Jacob","Boyd");
        assertTrue(existMedicalRecord);
    }
}
